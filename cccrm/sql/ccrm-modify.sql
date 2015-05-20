# Name: hujian
# Date: 2015-04-24 16:13

---------sys_user修改user_message->user_id-----------------
alter table sys_user rename column user_message to user_id;

----------programmer添加position字段---------------
alter table programmer add position varchar(30) DEFAULT '';