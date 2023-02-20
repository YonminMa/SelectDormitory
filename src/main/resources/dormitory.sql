create table beds
(
    id        int(10) auto_increment comment '主键，自增'
        primary key,
    uid       int(10) default 0 null comment 'user表中的id',
    room_id   int(10)           not null comment 'room表中的id',
    name      varchar(100)      not null comment '床编号',
    order_num int(10)           not null comment '排序号码，号码小的在前面',
    is_valid  int(10) default 1 not null comment '是否有效，无效的床位不参加在线选择',
    remarks   varchar(1000)     not null comment '备注，仅管理员可见',
    is_del    int(10) default 0 not null comment '1代表删除',
    status    int(10) default 0 not null comment '0 空闲 1 已选'
);

create table building
(
    id          int(10) auto_increment comment '主键，自增'
        primary key,
    name        varchar(100)                                                        not null comment '楼名称',
    description varchar(1000)                                                       not null comment '描述，可支持富文本，用户可见',
    image_url   varchar(100) default 'https://photo.tuchong.com/15447/f/163644.jpg' not null comment '图片地址，仅支持一张图片'
);

create table info
(
    id                int(10)       not null comment 'user表中的id',
    name              varchar(100)  not null comment '姓名',
    gender            int(10)       not null comment '性别：0为女 1为男',
    email             varchar(100)  not null comment '电子邮箱',
    tel               varchar(100)  not null comment '电话号码',
    last_login_time   int default 0 not null comment '最后一次登录时间',
    verification_code varchar(100)  not null,
    class_name        varchar(100)  null,
    type              int default 1 not null comment '1为学生。此字段保留方便以后扩展'
);

create table logs
(
    id          int(10) auto_increment comment '主键，自增'
        primary key,
    uid         int(10) default 0 null comment '操作用户id',
    operation   varchar(500)      not null comment '操作模块名称',
    client_ip   varchar(50)       not null comment '客户端IP',
    create_time int               not null comment '创建时间',
    content     varchar(1000)     not null comment '操作详情',
    status      int(10) default 0 not null comment '0 成功 1 失败',
    is_del      int(10) default 0 not null comment '1代表删除'
);

create table orders
(
    id          int(10) auto_increment comment '主键，自增'
        primary key,
    uid         int(10)      default 0                    null comment '提交人，user表中的id',
    group_id    int(10)      default 0                    not null comment 'groups表中的id，如果未组队，该值为0',
    room_id     int(10)                                   null comment 'room表中的id',
    building_id int(10)                                   not null comment 'building表中的id',
    submit_time timestamp(4)                              null comment '下单时间，用户提交表单时间',
    create_time timestamp(4) default CURRENT_TIMESTAMP(4) not null comment '订单生成时间，仅后台可见',
    finish_time timestamp(4)                              null comment '订单完成时间，仅后台可见',
    status      int(10)      default 0                    not null comment '0未处理，1处理成功，2处理失败'
);

create table permission
(
    id      int auto_increment comment '资源id'
        primary key,
    name    varchar(10)  not null comment '资源名称',
    path    varchar(100) not null comment '资源路径',
    comment varchar(10)  not null comment '资源描述',
    constraint comment
        unique (comment),
    constraint name
        unique (name),
    constraint path
        unique (path)
);

create table role
(
    id      int auto_increment comment '角色id'
        primary key,
    name    varchar(10) not null comment '角色名称',
    comment varchar(10) not null comment '角色描述',
    constraint comment
        unique (comment),
    constraint name
        unique (name)
);

create table role_permission
(
    role_id       int not null comment '角色id',
    permission_id int not null comment '资源id'
);

create table room
(
    id          int(10) auto_increment comment '主键，自增'
        primary key,
    building_id int(10)           not null comment 'buildings表中的id',
    name        varchar(100)      not null comment '宿舍名称',
    gender      int(10)           not null comment '性别：0为女 1为男',
    rest        int               not null,
    is_valid    int(10) default 1 not null comment '是否有效，无效的宿舍不参加在线选择',
    is_del      int(10) default 0 not null comment '1代表删除'
);

create table student_info
(
    id                int(10) auto_increment comment '主键，自增'
        primary key,
    uid               int(10)                     not null comment 'user表中的id',
    username          varchar(50)                 not null comment '学号',
    verification_code varchar(10)  default '0'    not null comment '校验码',
    class_id          int(10)                     not null comment '班级id',
    remarks           varchar(1000)               not null comment '备注，仅管理员可见',
    status            int(10)      default 0      not null comment '备用',
    password          varchar(100) default 'user' not null comment '密码'
);

create table sys
(
    id        int(10) auto_increment comment '主键，自增'
        primary key,
    key_name  varchar(100)  not null comment '配置名称',
    key_value varchar(1000) not null comment '配置值'
);

create table team
(
    id           int(10) auto_increment comment '主键，自增'
        primary key,
    name         varchar(100)      not null comment '队伍名称',
    invite_code  varchar(100)      not null comment '加入口令',
    description  varchar(1000)     not null comment '队伍描述，用户可见',
    member_count int     default 1 not null,
    status       int(10) default 0 not null comment '如果改组分配宿舍成功，置为1，将不可以再进行队伍的操作'
);

create table user
(
    id       int(10) auto_increment comment '主键，自增'
        primary key,
    username varchar(50)                not null comment '用户名',
    password varchar(64) default 'user' not null comment '密码',
    constraint username
        unique (username)
);

create table user_role
(
    user_id int not null comment '用户id',
    role_id int not null comment '角色id'
);

create table user_room
(
    user_id int not null comment '用户id',
    room_id int not null comment '房间id',
    constraint user_id
        unique (user_id)
);

create table user_team
(
    id        int(10) auto_increment comment '主键，自增'
        primary key,
    user_id   int(10)                                   not null comment 'user表中的id',
    is_leader int          default 0                    not null comment '1是队长，0不是队长',
    team_id   int(10)                                   not null comment 'team表中的id',
    join_time timestamp(4) default CURRENT_TIMESTAMP(4) not null comment '加入时间'
);

