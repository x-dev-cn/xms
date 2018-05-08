delete from sys_user;
delete from sys_app;
delete from sys_user_app_roles;
delete from sys_role;
delete from sys_resource;
delete from sys_organization;

insert into sys_user values(1,1,'admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f', '管理员', '18908660866', '2017-09-10', false);

insert into sys_app values(1, '中心服务器', 'eea88990-0c46-42a0-9805-e9fc82efc4dc', '87461224-c735-4eb7-b107-8690c0fc396a', true);
insert into sys_app values(2, '后台演示', '20c65d83-751c-41bb-b143-8efc6425b753', '65c0a13d-726a-4c36-83ed-1c6bba26c008', true);

insert into sys_organization values(1, '总公司', 0, '0/', 1, true);
insert into sys_organization values(2, '分公司1', 1, '0/1/', 1,true);
insert into sys_organization values(3, '分公司2', 1, '0/1/', 2,true);
insert into sys_organization values(4, '分公司11', 2, '0/1/2/', 1,true);


insert into sys_user_app_roles values(1, 1, 1, '1');

insert into sys_resource values(1, '资源', 'menu', 'javascript:;', 0, '0/', '', true);

insert into sys_resource values(2, '系统管理', 'menu', 'javascript:;', 1, '0/1/', 'sys:*', true);

insert into sys_resource values(11, '应用管理', 'menu', '/auth/app', 2, '0/2/', 'sys:app:*', true);
insert into sys_resource values(12, '应用新增', 'button', '', 11, '0/2/11/', 'sys:app:create', true);
insert into sys_resource values(13, '应用修改', 'button', '', 11, '0/2/11/', 'sys:app:update', true);
insert into sys_resource values(14, '应用删除', 'button', '', 11, '0/2/11/', 'sys:app:delete', true);
insert into sys_resource values(15, '应用查看', 'button', '', 11, '0/2/11/', 'sys:app:view', true);

insert into sys_resource values(21, '资源管理', 'menu', '/auth/resource', 2, '0/2/', 'sys:resource:*', true);
insert into sys_resource values(22, '资源新增', 'button', '', 21, '0/2/21/', 'sys:resource:create', true);
insert into sys_resource values(23, '资源修改', 'button', '', 21, '0/2/21/', 'sys:resource:update', true);
insert into sys_resource values(24, '资源删除', 'button', '', 21, '0/2/21/', 'sys:resource:delete', true);
insert into sys_resource values(25, '资源查看', 'button', '', 21, '0/2/21/', 'sys:resource:view', true);

insert into sys_resource values(31, '角色管理', 'menu', '/auth/role', 2, '0/2/', 'sys:role:*', true);
insert into sys_resource values(32, '角色新增', 'button', '', 31, '0/2/31/', 'sys:role:create', true);
insert into sys_resource values(33, '角色修改', 'button', '', 31, '0/2/31/', 'sys:role:update', true);
insert into sys_resource values(34, '角色删除', 'button', '', 31, '0/2/31/', 'sys:role:delete', true);
insert into sys_resource values(35, '角色查看', 'button', '', 31, '0/2/31/', 'sys:role:view', true);

insert into sys_resource values(41, '授权管理', 'menu', '/auth/authorization', 2, '0/2/', 'sys:authorization:*', true);
insert into sys_resource values(42, '授权新增', 'button', '', 41, '0/2/41/', 'sys:authorization:create', true);
insert into sys_resource values(43, '授权修改', 'button', '', 41, '0/2/41/', 'sys:authorization:update', true);
insert into sys_resource values(44, '授权删除', 'button', '', 41, '0/2/41/', 'sys:authorization:delete', true);
insert into sys_resource values(45, '授权查看', 'button', '', 41, '0/2/41/', 'sys:authorization:view', true);

insert into sys_resource values(51, '用户管理', 'menu', '/auth/user', 2, '0/2/', 'sys:user:*', true);
insert into sys_resource values(52, '用户新增', 'button', '', 51, '0/2/51/', 'sys:user:create', true);
insert into sys_resource values(53, '用户修改', 'button', '', 51, '0/2/51/', 'sys:user:update', true);
insert into sys_resource values(54, '用户删除', 'button', '', 51, '0/2/51/', 'sys:user:delete', true);
insert into sys_resource values(55, '用户查看', 'button', '', 51, '0/2/51/', 'sys:user:view', true);

insert into sys_resource values(61, '组织机构管理', 'menu', '/auth/organization', 2, '0/2/', 'sys:organization:*', true);
insert into sys_resource values(62, '组织机构新增', 'button', '', 61, '0/2/61/', 'sys:organization:create', true);
insert into sys_resource values(63, '组织机构修改', 'button', '', 61, '0/2/61/', 'sys:organization:update', true);
insert into sys_resource values(64, '组织机构删除', 'button', '', 61, '0/2/61/', 'sys:organization:delete', true);
insert into sys_resource values(65, '组织机构查看', 'button', '', 61, '0/2/61/', 'sys:organization:view', true);

insert into sys_resource values(90, '审核', 'menu', '/auth/cms/audit', 1, '0/1/', 'audit:*', true);

insert into sys_resource values(100, '新闻动态', 'menu', 'javascript:;', '1', '0/1/', 'cms:news:*', true);


insert into sys_role values(1, 'admin', '超级管理员', '2,90,100,120,150,190,220,240,', true);
insert into sys_role values(2, 'role1', 'APP1管理员', '11,21,31,41,51,61', true);
insert into sys_role values(3, 'role2', 'APP2管理员', '11,21,31,41,51,61', true);