	/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.moneyExchangeSep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.entities.MoneyExchangeSep;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractPerformService;

@Service
public class AuthenticatedMoneyExchangeSepPerformService implements AbstractPerformService<Authenticated, MoneyExchangeSep> {

	@Autowired
	protected AuthenticatedMoneyExchangeSepRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationSepRepository systemConfigRepository;


	@Override
	public boolean authorise(final Request<MoneyExchangeSep> request) {
		assert request != null;

		return request.getPrincipal().isAuthenticated();
	}

	@Override
	public void bind(final Request<MoneyExchangeSep> request, final MoneyExchangeSep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Money source = new Money();
		final String acceptedCurrencies = this.repository.findAcceptedCurrencies();

		try {
			final String currency = entity.getSource().getCurrency();

			final boolean currencyIsSuportedS = acceptedCurrencies.contains(currency);

			source = request.getModel().getAttribute("source", Money.class);
			if (!currencyIsSuportedS) {
				throw new Exception();
			}
		} catch (final Exception e) {
			source.setAmount(1.);
			source.setCurrency(this.repository.findSystemCurrency());
		}

		request.getModel().setAttribute("source", source);
		request.bind(entity, errors, "source", "targetCurrency", "date", "change");

	}

	@Override
	public void unbind(final Request<MoneyExchangeSep> request, final MoneyExchangeSep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		if(entity.getSource() == null || entity.getTargetCurrency() == null) {
			if(entity.getSource() == null ) {
				model.setAttribute("source", "");
				model.setAttribute("change", "");
			}
			else {
				model.setAttribute("source", entity.getSource());
				model.setAttribute("change", entity.getChange());
			}
			if(entity.getTargetCurrency() == null ) {
				model.setAttribute("targetCurrency", "");
				model.setAttribute("change", "");
			}
			else {
				model.setAttribute("targetCurrency", entity.getTargetCurrency());
				model.setAttribute("change", entity.getChange());
			}
			request.unbind(entity, model, "date");
		}
		
		else {
			request.unbind(entity, model, "source", "targetCurrency", "date", "change");
		}

	}

	@Override
	public MoneyExchangeSep instantiate(final Request<MoneyExchangeSep> request) {
		assert request != null;

		MoneyExchangeSep result;

		result = new MoneyExchangeSep();
		result.setRate(1.0);
		final Money source = new Money();
		source.setAmount(1.);
		source.setCurrency(this.repository.findSystemCurrency());
		result.setSource(source);
		result.setTargetCurrency(this.repository.findSystemCurrency());
		result.setChange(source);

		final Calendar c = Calendar.getInstance();
		final Date d = c.getTime();
		result.setDate(d);

		return result;
	}

	@Override
	public void validate(final Request<MoneyExchangeSep> request, final MoneyExchangeSep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String[] acceptedCurrencies=this.systemConfigRepository.findAcceptedCurrencies().split(",");
		final List<String> currencies = Arrays.asList(acceptedCurrencies);
		
		if(!errors.hasErrors("source")) {
	
			errors.state(request, currencies.contains(entity.getSource().getCurrency()), 
				"source", "authenticated.money-exchange-sep.form.error.source.currency-not-supported");
			errors.state(request, entity.getSource().getAmount()>=0., 
				"source", "authenticated.money-exchange-sep.form.error.source.negative");
		}
		if(!errors.hasErrors("targetCurrency")) {
			
			errors.state(request, currencies.contains(entity.getSource().getCurrency()), 
				"targetCurrency", "authenticated.money-exchange.form.error.target-currency.currency-not-supported");
			errors.state(request, entity.getSource().getAmount()>=0., 
				"targetCurrency", "authenticated.money-exchange.form.error.target-currency.negative");
		}

	}

	@Override
	public void perform(final Request<MoneyExchangeSep> request, final MoneyExchangeSep entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		final String targetCurrency;
		MoneyExchangeSep exchange = null;

		targetCurrency = request.getModel().getAttribute("targetCurrency", String.class);

		final String acceptedCurrencies = this.repository.findAcceptedCurrencies();
		final Money source = new Money();

		if (!errors.hasErrors("source")) {
			final String currency = entity.getSource().getCurrency();

			final boolean currencyIsSuportedS = acceptedCurrencies.contains(currency);

			if (!currencyIsSuportedS) {
				source.setAmount(1.);
				source.setCurrency(this.repository.findSystemCurrency());
				entity.setSource(source);
			}

			errors.state(request, currencyIsSuportedS, "source", "authenticated.money-exchange-sep.form.error.source.currency-not-supported");
			errors.state(request, entity.getSource().getAmount() >= 0, "source", "authenticated.money-exchange-sep.form.error.source.negative");

		}

		if (!errors.hasErrors("targetCurrency")) {

			final boolean currencyIsSuportedT = acceptedCurrencies.contains(entity.getTargetCurrency());
			errors.state(request, currencyIsSuportedT, "targetCurrency", "authenticated.money-exchange-sep.form.error.source.currency-not-supported");
		}

		if (!errors.hasErrors()) {
			exchange = this.computeMoneyExchange(entity.getSource(), targetCurrency);

			entity.setRate(exchange.getRate());
			entity.setDate(exchange.getDate());
			final Money target = new Money();
			//			target.setAmount(entity.getSource().getAmount() * entity.getRate());
			//			target.setCurrency(entity.getTargetCurrency());
			target.setAmount(exchange.getChange().getAmount());
			target.setCurrency(exchange.getChange().getCurrency());
			entity.setChange(target);

		}

		errors.state(request, exchange != null, "*", "authenticated.money-exchange.form.label.api-error");

	}

	// Ancillary methods ------------------------------------------------------

	public MoneyExchangeSep computeMoneyExchange(final Money moneyToCompute, final String targetCurrency) {
		assert moneyToCompute != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchangeSep change;
		RestTemplate api;
		ExchangeRate record;
		Double rate;
		final Money changeMoney = new Money();

		final double scale = Math.pow(10, 2);

		final Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		final Date yesterday = c.getTime();

		change = this.repository.findMoneyExchangeFromCurrencies(moneyToCompute.getCurrency(), targetCurrency);

		if (change == null || change.getDate().before(yesterday)) {

			api = new RestTemplate();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRate.class, //
				moneyToCompute.getCurrency(), //
				targetCurrency //
			);

			assert record != null;
			rate = record.getRates().get(targetCurrency);

			change = new MoneyExchangeSep();
			final Money sourceMoney = new Money();
			sourceMoney.setAmount(moneyToCompute.getAmount());
			sourceMoney.setCurrency(moneyToCompute.getCurrency());
			change.setSource(sourceMoney);
			change.setTargetCurrency(targetCurrency);
			change.setRate(rate);
			change.setDate(record.getDate());

			final double d = moneyToCompute.getAmount() * change.getRate();
			final double amount = Math.round(d * scale) / scale;
			changeMoney.setAmount(amount);
			changeMoney.setCurrency(targetCurrency);
			change.setChange(changeMoney);

			
			this.repository.save(change);

		} else {
			final double d = moneyToCompute.getAmount() * change.getRate();
			final double amount = Math.round(d * scale) / scale;
			changeMoney.setAmount(amount);
			changeMoney.setCurrency(targetCurrency);
			change.setChange(changeMoney);
		}

		return change;

	}

	public List<Money> convertMoney(final List<Money> ls, final String targetCurrency) {
		final List<Money> resLs = new ArrayList<>();
		for (final Money price : ls) {
			if (price.getAmount() == null) {
				price.setAmount(0.);
				price.setCurrency(targetCurrency);
			} else if (!price.getCurrency().equals(targetCurrency)) {
				Money aux;
				aux = (this.computeMoneyExchange(price, targetCurrency).getChange());
				price.setAmount(aux.getAmount());
				price.setCurrency(aux.getCurrency());
			}
			resLs.add(price);
		}
		return resLs;
	}

}
