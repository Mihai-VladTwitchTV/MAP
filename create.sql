



--run maybe here ???? idk bro this is a mess
USE employeedirectory;
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
-- stuff for the adapter & decorator part of things, sorry for not matching the syntax im lazy
CREATE TABLE Computer (
                          id INT PRIMARY KEY,
                          brand_name VARCHAR(255) NOT NULL
);

-- Create Computer_Monitor table
CREATE TABLE Computer_Monitor (
                                  id INT PRIMARY KEY,
                                  brand_name VARCHAR(255) NOT NULL
);

-- Create TV table
CREATE TABLE TV (
                    id INT PRIMARY KEY,
                    brand_name VARCHAR(255) NOT NULL
);

-- Create TVToMonitorAdapter table
CREATE TABLE TVTo_Monitor_Adapter (
                                      id INT PRIMARY KEY,
                                      tv INT NOT NULL,
                                      FOREIGN KEY (tv) REFERENCES TV(id)
);

CREATE TABLE Extended_Computer_Decorator (
                                             id INT PRIMARY KEY,
                                             connector_Type VARCHAR(255),
                                             latency INT,
                                             computer INT,
                                             FOREIGN KEY (computer) REFERENCES Computer(id)
);
-------------------------------------------------------------------------------------------------
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table computer (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table computer_monitor (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table connector (id integer not null, latency integer, type varchar(255), primary key (id)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table extended_computer_decorator (computer integer not null, connector integer not null, id integer not null, primary key (id)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
create table tv (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table tvto_monitor_adapter (id integer not null, tv integer not null, primary key (id)) engine=InnoDB;
alter table extended_computer_decorator add constraint UK_8sl5fu2g4sx77e0cg6d09c5fn unique (computer);
alter table extended_computer_decorator add constraint UK_e2vdr79f75j4s7c8o3665vo8f unique (connector);
alter table tvto_monitor_adapter add constraint UK_dwp7mk8yjuka2qexr59tcvt7b unique (tv);
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table extended_computer_decorator add constraint FKtly79pbhl3bqxhcm3h9xcew29 foreign key (computer) references computer (id);
alter table extended_computer_decorator add constraint FKohm61396dm270rs5ulk2qcn31 foreign key (connector) references connector (id);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
alter table tvto_monitor_adapter add constraint FKdl0dbru1fbouyehe6hsrmmtmc foreign key (tv) references tv (id);
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table computer (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table computer_monitor (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table connector (id integer not null, latency integer, type varchar(255), primary key (id)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table extended_computer_decorator (computer integer not null, connector integer not null, id integer not null, primary key (id)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
create table tv (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table tvto_monitor_adapter (id integer not null, tv integer not null, primary key (id)) engine=InnoDB;
alter table extended_computer_decorator add constraint UK_8sl5fu2g4sx77e0cg6d09c5fn unique (computer);
alter table extended_computer_decorator add constraint UK_e2vdr79f75j4s7c8o3665vo8f unique (connector);
alter table tvto_monitor_adapter add constraint UK_dwp7mk8yjuka2qexr59tcvt7b unique (tv);
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table extended_computer_decorator add constraint FKtly79pbhl3bqxhcm3h9xcew29 foreign key (computer) references computer (id);
alter table extended_computer_decorator add constraint FKohm61396dm270rs5ulk2qcn31 foreign key (connector) references connector (id);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
alter table tvto_monitor_adapter add constraint FKdl0dbru1fbouyehe6hsrmmtmc foreign key (tv) references tv (id);
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table computer (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table computer_monitor (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table connector (id integer not null, latency integer, type varchar(255), primary key (id)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table extended_computer_decorator (computer integer not null, connector integer not null, id integer not null, primary key (id)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
create table tv (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table tvto_monitor_adapter (id integer not null, tv integer not null, primary key (id)) engine=InnoDB;
alter table extended_computer_decorator add constraint UK_8sl5fu2g4sx77e0cg6d09c5fn unique (computer);
alter table extended_computer_decorator add constraint UK_e2vdr79f75j4s7c8o3665vo8f unique (connector);
alter table tvto_monitor_adapter add constraint UK_dwp7mk8yjuka2qexr59tcvt7b unique (tv);
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table extended_computer_decorator add constraint FKtly79pbhl3bqxhcm3h9xcew29 foreign key (computer) references computer (id);
alter table extended_computer_decorator add constraint FKohm61396dm270rs5ulk2qcn31 foreign key (connector) references connector (id);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
alter table tvto_monitor_adapter add constraint FKdl0dbru1fbouyehe6hsrmmtmc foreign key (tv) references tv (id);
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table computer (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table computer_monitor (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table extended_computer_decorator (computer integer not null, id integer not null, latency integer, connector_type varchar(255), primary key (id)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
create table tv (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table tvto_monitor_adapter (id integer not null, tv integer not null, primary key (id)) engine=InnoDB;
alter table extended_computer_decorator add constraint UK_8sl5fu2g4sx77e0cg6d09c5fn unique (computer);
alter table tvto_monitor_adapter add constraint UK_dwp7mk8yjuka2qexr59tcvt7b unique (tv);
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table extended_computer_decorator add constraint FKtly79pbhl3bqxhcm3h9xcew29 foreign key (computer) references computer (id);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
alter table tvto_monitor_adapter add constraint FKdl0dbru1fbouyehe6hsrmmtmc foreign key (tv) references tv (id);
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table computer (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table computer_monitor (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table extended_computer_decorator (computer integer not null, id integer not null, latency integer, connector_type varchar(255), primary key (id)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
create table tv (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table tvto_monitor_adapter (id integer not null, tv integer not null, primary key (id)) engine=InnoDB;
alter table extended_computer_decorator add constraint UK_8sl5fu2g4sx77e0cg6d09c5fn unique (computer);
alter table tvto_monitor_adapter add constraint UK_dwp7mk8yjuka2qexr59tcvt7b unique (tv);
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table extended_computer_decorator add constraint FKtly79pbhl3bqxhcm3h9xcew29 foreign key (computer) references computer (id);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
alter table tvto_monitor_adapter add constraint FKdl0dbru1fbouyehe6hsrmmtmc foreign key (tv) references tv (id);
create table assignments (id integer not null, projectid integer, assignment_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table client (clientid integer not null, phone_number integer, email_address varchar(255), first_name varchar(255), last_name varchar(255), primary key (clientid)) engine=InnoDB;
create table computer (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table computer_monitor (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table department (departmentid integer not null, max_employees integer, specialization varchar(255), primary key (departmentid)) engine=InnoDB;
create table employee (assignmentid integer, departmentid integer, employeeid integer not null, is_full_time bit, is_leader bit, is_part_time bit, phone_number integer, dtype varchar(31) not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), type varchar(255), primary key (employeeid)) engine=InnoDB;
create table employee_skill (employeeid integer not null, skill_level integer, skillid integer not null, primary key (employeeid, skillid)) engine=InnoDB;
create table extended_computer_decorator (computer integer not null, id integer not null, latency integer, connector_type varchar(255), primary key (id)) engine=InnoDB;
create table meetings (departmentid integer, meetingid integer not null, end_date datetime(6), start_date datetime(6), location varchar(255), meeting_type varchar(255), title varchar(255), primary key (meetingid)) engine=InnoDB;
create table meetings_employee (employeeid integer not null, meetingid integer not null, attendance_status varchar(255), primary key (employeeid, meetingid)) engine=InnoDB;
create table project (clientid integer, departmentid integer, projectid integer not null, end_date datetime(6), start_date datetime(6), meeting_type varchar(255), project_name varchar(255), status varchar(255), primary key (projectid)) engine=InnoDB;
create table project_costs (amount decimal(38,2), costid integer not null, projectid integer, due_date datetime(6), description varchar(255), expense_type varchar(255), primary key (costid)) engine=InnoDB;
create table project_milestones (milestoneid integer not null, projectid integer, description varchar(255), milestone_name varchar(255), primary key (milestoneid)) engine=InnoDB;
create table skill (skillid integer not null, description varchar(255), skill_name varchar(255), primary key (skillid)) engine=InnoDB;
create table tv (id integer not null, brand_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table tvto_monitor_adapter (id integer not null, tv integer not null, primary key (id)) engine=InnoDB;
alter table extended_computer_decorator add constraint UK_8sl5fu2g4sx77e0cg6d09c5fn unique (computer);
alter table tvto_monitor_adapter add constraint UK_dwp7mk8yjuka2qexr59tcvt7b unique (tv);
alter table assignments add constraint FKevt3nck11bne8jjgew4g7yl5y foreign key (projectid) references project (projectid);
alter table employee add constraint FKf2ijeqhteyp1d7q61utfk741m foreign key (assignmentid) references assignments (id);
alter table employee add constraint FKpqnkyknoe4bmsfhnwdfaans5r foreign key (departmentid) references department (departmentid);
alter table employee_skill add constraint FK9r6s98d2ad6bmcnt5lvbl551p foreign key (employeeid) references employee (employeeid);
alter table employee_skill add constraint FKo46q716jpkfqcdvkoh0purnff foreign key (skillid) references skill (skillid);
alter table extended_computer_decorator add constraint FKtly79pbhl3bqxhcm3h9xcew29 foreign key (computer) references computer (id);
alter table meetings add constraint FK38b9q7pil7nt5pnpo3ntdhxav foreign key (departmentid) references department (departmentid);
alter table meetings_employee add constraint FKbcuw4v7xnkxys5eawh8hm21wp foreign key (employeeid) references employee (employeeid);
alter table meetings_employee add constraint FK9y6brud2cudqov2ptq4au1wou foreign key (meetingid) references meetings (meetingid);
alter table project add constraint FKi6mikgf1ffvc446f1f6ks7mn1 foreign key (clientid) references client (clientid);
alter table project add constraint FKgpik1em37r29wrfvj6go5aorm foreign key (departmentid) references department (departmentid);
alter table project_costs add constraint FK744oemvlmen9oto3pfh9mu543 foreign key (projectid) references project (projectid);
alter table project_milestones add constraint FKihu7npm6x97ka7wv8f5fsu4t2 foreign key (projectid) references project (projectid);
alter table tvto_monitor_adapter add constraint FKdl0dbru1fbouyehe6hsrmmtmc foreign key (tv) references tv (id);
