drop database if exists `Acme-Toolkits-22.7`;
create database `Acme-Toolkits-22.7`;

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Toolkits-22.7`.* to 'acme-user'@'%';
