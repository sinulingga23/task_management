create table if not exists public.task_management (
	id uuid primary key,
	name varchar(150) not null,
	description text not null,
	task_status varchar(50) null,
	created_at timestamp not null,
	created_by varchar(150) not null,
	updated_at timestamp null,
	updated_by varchar(150) null
);

INSERT INTO public.task_management (id,"name",description,task_status,created_at,created_by,updated_at,updated_by) VALUES
	 ('1ccd9857-88cb-47a0-b33b-d7003e327fab','Grooming','Don''t forget to attend the Grooming meeting at 3 PM','INCOMPLETE','2023-12-06 18:38:58.826318','System',NULL,NULL),
	 ('729acdfe-76e4-45a7-8306-1587133b1c24','Technical Meeting','Don''t forget to attend the Technical meeting at 3 PM','COMPLETE','2023-12-06 18:38:52.186867','System','2023-12-06 18:41:12.526191','System'),
	 ('730a8414-d683-43cb-94c0-ef2c97065179','Sprint Planning','Don''t forget to attend the spring planning meeting at 3 PM','COMPLETE','2023-12-06 18:39:04.762585','System','2023-12-06 18:45:15.493253','System');
