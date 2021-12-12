<h1>企业入驻 API 文档</h1

> 数据表仅供后端编码参考，前端界面不需理会

[TOC]



 

------



# 注册

**请求URL：**

- `/api/register`

**请求方式：**

- POST

**参数：**

领导账户

~~~json
{
    "name":"<String>",						//用户名
    "password":"<String>",				//密码
    "email":"<String>"						//邮箱
}
~~~

**返回值：**

请求成功会返回一个唯一的授权凭证（Bearer Token）

```json
{
    "data":{
        "userId":"<String>",
    },
    "code":0,
    "msg":"success"
}
```

token 保存时间待定

**请求失败：**

~~~json
{
    "data":null,
    "code":-1,
    "msg":"注册失败"
}
~~~





# 登录（login）

**简要描述：**

- 管理员登录
- 迁入和独立注册企业登录
- 新成立企业或非独立注册企业登录

## 领导登录

**请求URL：**

- `/api/login/leader`

**请求方式：**

- POST

**参数：**

领导账户

~~~json
{
    "username":"<String>",						//用户名
    "password":"<String>"				//密码
}
~~~

**返回值：**

请求成功会返回一个唯一的授权凭证（Bearer Token）

```json
{
    "data":{
        "token":"<String>",
    },
    "code":0,
    "msg":"success"
}
```

token 保存时间待定

**请求失败：**

~~~json
{
    "data":null,
    "code":-1,
    "msg":"用户不存在或密码错误"
}
~~~



## 管理员登录

**请求URL：**

- `/api/login/admin`

**请求方式：**

- POST

**参数：**

管理员唯一账号

~~~json
{
    "username":"<String>",						//用户名
    "password":"<String>"				//密码
}
~~~

**返回值：**

请求成功会返回一个唯一的授权凭证（Bearer Token）

```json
{
    "data":{
        "token":"<String>",
    },
    "code":0,
    "msg":"success"
}
```

> token 保存时间一天

**请求失败：**

~~~json
{
    "data":null,
    "code":-1,
    "msg":"用户不存在或密码错误"
}
~~~



## 普通用户登录

**请求URL：**

- `/api/login/common`

**请求方式：**

- POST

**参数：**

管理员唯一账号

~~~json
{
    "name":"<String>",						//用户名
    "password":"<String>"				//密码
}
~~~

**返回值：**

请求成功会返回一个唯一的授权凭证（Bearer Token）

```json
{
    "data":{
        "token":"<String>",
    },
    "code":0,
    "msg":"success"
}
```

> token 保存时间一天

**请求失败：**

~~~json
{
    "data":null,
    "code":-1,
    "msg":"用户不存在或密码错误"
}
~~~



------



# 迁入和独立注册企业（old)

## **数据表：**

### 入园申请表

##### 表名：old （主表）

> 根据入园申请表填写

|       字段名        |    类型    |                             说名                             |
| :-----------------: | :--------: | :----------------------------------------------------------: |
|       old_id        |   String   |                          old表主键                           |
|     credit_code     |   String   |                 统一社会信用代码（18位字符）                 |
|  organization_code  |   String   |                         组织机构代码                         |
|      password       |   String   |                         企业注册密码                         |
|        name         |   String   |                       申请入驻企业名称                       |
|      represent      |   String   |                           法人代表                           |
|   represent_phone   |   String   |                       法人代表联系电话                       |
|   represent_email   |   String   |                       法人代表邮箱地址                       |
|        agent        |   String   |                            经办人                            |
|     agent_phone     |   String   |                        经办人联系电话                        |
|     agent_email     |   String   |                        经办人邮箱地址                        |
|  register_address   |   String   |                           注册地址                           |
|       license       | MediumBolb |                       新的营业执照上传                       |
|  register_capital   |   String   |                       注册资本（万元）                       |
|    real_address     |   String   |                         实际经营地址                         |
|    real_capital     |   String   |                           实收资本                           |
|     last_income     |   String   |                        上年度经营收入                        |
|      last_tax       |   String   |                          上年度利税                          |
|      employees      |   String   |                           员工人数                           |
|    origin_number    |   String   |                         初始入园人数                         |
|      set_date       | Timestamp  |                           成立日期                           |
|       nature        |   String   |                           企业性质                           |
|     certificate     | MediumBolb |          教师需要上传教师资格证/学生需要上传学生证           |
|      involved       |   String   |                           所属行业                           |
|    main_business    |   String   |                           主营业务                           |
|         way         |   String   |                           入园方式                           |
|      business       |   String   |                           入园业务                           |
|    old_demand_id    |   String   |         园区场地租赁需求   -->到 oldDemand 表的外键          |
| old_shareholder_id  |   String   |           股东构成  -->到 oldSharehoolder 表的外键           |
|  old_mainperson_id  |   String   |          主要人员介绍  -->到 oldMainPerson表的外键           |
|   old_project_id    |   String   |       入园项目简要介绍和分析  -->到 oldProject表的外键       |
| old_intellectual_id |   String   |         知识产权情况  -->到 oldIntellectual表的外键          |
|   old_funding_id    |   String   |    承担财政资金资助项目及获奖情况  -->到 funding表的外键     |
|     cooperation     |   String   |                     以往与桂电的合作情况                     |
|     suggestion      |   String   |                          科技园意见                          |
|        note         |   String   |                             备注                             |
|        state        |   String   | 授权状态（初始为 null，待审核为 0，审核不通过为 1，审核通过为 2） |
|     submit_time     |   String   |                           提交时间                           |
|        room         |   String   |                            房间号                            |
|      inApplyId      |   String   |          众创空间入驻申请书 -->到 InApply 表的外键           |
|     outApplyId      |   String   |           科技园退租申请书 -->到 OutApply 表的外键           |

##### 表名：old_demand （园区场地租赁需求）

|    字段名     |  类型  |        说明        |
| :-----------: | :----: | :----------------: |
|      id       | String |        UUID        |
| old_demand_id | String |        UUID        |
|  lease_area   | String | 租赁面积（平方米） |
|   position    | String |      位置需求      |
|     lease     | String |     租期（年）     |
|     floor     | String |      楼层需求      |
|   electric    | String |      电力需求      |
|     water     | String |     给排水需求     |
|      web      | String |      网络需求      |
|    others     | String |      其他需求      |

##### 表名：old_shareholder（股东构成）

|       字段名       |  类型  |      说明      |
| :----------------: | :----: | :------------: |
|         id         | String |      UUID      |
| old_shareholder_id | String |      UUID      |
|        name        | String | 股东姓名或名称 |
|       stake        | String |    股份比例    |
|       nature       | String |    股东性质    |

##### 表名：old_mainperson（主要人员介绍）

|      字段名       |  类型  |   说明   |
| :---------------: | :----: | :------: |
|        id         | String |   UUID   |
| old_mainperson_id | String |   UUID   |
|       name        | String |   姓名   |
|       born        | String | 出生年月 |
|        job        | String |   职务   |
|      school       | String | 毕业学校 |
|       title       | String |   职称   |
|    background     | String |   学历   |
|   professional    | String |   专业   |

##### 表名：old_project（入园项目简要介绍和分析）

|     字段名     |  类型  |     说明     |
| :------------: | :----: | :----------: |
|       id       | String |     UUID     |
| old_project_id | String |     UUID     |
| project_brief  | String |   项目简介   |
|   advantage    | String | 竞争优势分析 |
|     market     | String | 市场前景分析 |
|     energy     | String |   能耗分析   |
|   pollution    | String |   污染情况   |
|     noise      | String |   噪音情况   |
|     others     | String |   其他情况   |

##### 表名：old_intellectual（知识产权情况）

|       字段名        |    类型    |          说明          |
| :-----------------: | :--------: | :--------------------: |
|         id          |   String   |          UUID          |
| old_intellectual_id |   String   |          UUID          |
|        name         |   String   |          名称          |
|        kind         |   String   |          类别          |
|     apply_time      |   String   |        申请时间        |
|    approval_time    |   String   |        批准时间        |
|  intellectual_file  | MediumBolb | 知识产权证书等扫描文件 |

##### 表名：old_funding（承担财政资金资助项目及获奖情况）

|   字段名   |  类型  |       说明       |
| :--------: | :----: | :--------------: |
|     id     | String |       UUID       |
| funding_id | String |       UUID       |
|    name    | String |   项目奖项名称   |
|   level    | String |       级别       |
|    time    | String |       时间       |
|   grants   | String | 获得政府资助金额 |
|   award    | String |  颁奖部门/时间   |



### 导出信息表

|        字段名        |  类型  |                      说明                      |
| :------------------: | :----: | :--------------------------------------------: |
|      team_name       | String |                    企业名称                    |
|     credit_code      | String |                统一社会信用代码                |
|  organization_code   | String |                  组织机构代码                  |
|    register_time     | String |                    注册时间                    |
|      join_time       | String |                 入驻科技园时间                 |
|   register_capital   | String |                注册资金（千元）                |
|    register_kind     | String |                企业登记注册类型                |
|    industry_kind     | String |                    行业类型                    |
|        field         | String |                企业所属科技领域                |
| graduated_enterprise | String |                 是否是毕业企业                 |
|    graduated_time    | String |                    毕业时间                    |
|   high_enterprise    | String |               是否为高新技术企业               |
|     medium_sized     | String |              是否是科技型中小企业              |
| mentor_relationship  | String |           是否与创业导师建立辅导关系           |
|     header_kind      | String |                 主要负责人特征                 |
| serial_entrepreneur  | String |         企业主要负责人是否为连续创业者         |
|    header_gender     | String |               企业主要负责人性别               |
|       tax_kind       | String |                 企业纳税人类型                 |
|        header        | String |                   企业负责人                   |
|   statistic_header   | String |                   统计负责人                   |
|    submit_header     | String |                     填报人                     |
|     submit_phone     | String |                   填报人电话                   |
|     submit_time      | String |                    填报日期                    |
|   risk_investment    | String |         获天使投资或风险投资额（千元）         |
|         area         | String |          占用孵化器场地面积（平方米）          |
|     institutions     | String |                 研发机构（个）                 |
| total_transformation | String |               成果转换总数（个）               |
|       relying        | String |               依托高校数量（个）               |
|       winning        | String |                 获奖成果（个）                 |
|        result        | String |                 产出成果（个）                 |
|   incubate_income    | String |             在孵企业总收入（千元）             |
|   incubate_product   | String |          在孵企业工业生产总值（千元）          |
|   incubate_profit    | String |             在孵企业净利润（千元）             |
|     incubate_tax     | String |            在孵企业上缴税额（千元）            |
|     incubate_out     | String |            在孵企业出口总额（千元）            |
|       employee       | String |             在孵企业从业人员（人）             |
|        doctor        | String |                   博士（人）                   |
|        master        | String |                   硕士（人）                   |
|       graduate       | String |                研究生学历（人）                |
|       bachelor       | String |                 本科学历（人）                 |
|       college        | String |                 大专学历（人）                 |
|    tec_secondary     | String |                 中专学历（人）                 |
|    tec_activists     | String |               科技活动人员（人）               |
|      rad_number      | String |         研究与试验发展(R&D)人员（人）          |
|      returnees       | String |               留学回国人员（人）               |
|       talents        | String |               千人计划人数（人）               |
|       trainee        | String |      接纳大学生、研究生实习人员（人/天）       |
|      employment      | String |          接纳应届毕业生就业人员（人）          |
|     applications     | String |               当年知识产权申请数               |
| applications_patent  | String |                 其中：发明专利                 |
|       granted        | String |               当年知识产权授权数               |
|    granted_patent    | String |                 其中：发明专利                 |
|        valid         | String |               拥有有效知识产权数               |
|     valid_patent     | String |                 其中：发明专利                 |
|    soft_copyright    | String |                   软件著作权                   |
|    plant_variety     | String |                   植物新品种                   |
|      ic_layout       | String |                  集成电路布图                  |
|   foreign_patents    | String |                购买国外技术专利                |
| contract_transaction | String |                技术合同交易数量                |
|   contract_urnover   | String |                 技术合同交易额                 |
|     project_num      | String |          当年承担国家级科技计划项目数          |
|     total_awards     | String |                当年参赛获奖情况                |
|   province_awards    | String |                 其中：省级以上                 |
|     awards_file      |  File  |              提供证书复印或扫描件              |
|    under_projects    | String |                承担各类计划项目                |
|      under_file      |  File  | 提供相关项目协议扫描件（敏感信息可以模糊处理） |
|   national_project   | String |                   国家级项目                   |
|    school_project    | String |                校企联合申报项目                |
|   declaration_name   | String |                联合申报项目名称                |
|   declaration_num    | String |                联合申报项目金额                |
|     expenditure      | String |              科技活动经费支出总额              |
|   rad_expenditure    | String |      其中：研究与试验发展（R&D）经费支出       |
| product_expenditure  | String |            其中：新产品开发经费支出            |
|   government_grant   | String |                 其中：政府拨款                 |
|     self_raised      | String |                    企业自筹                    |



## 入园申请表填写

**简要描述：**

部分字段在注册时已经填写

**请求URL：**

- `/api/oldEnterprise`

**请求方式：**

- PUT（带token）

**参数：**

```json
{
    "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    "registerAddress":"<String>",									//注册地址
    "license":"<File>",														//新的营业执照上传
    "registerCapital":"<String>",									//注册资本（万元）
    "realAddress":"<String>",									//实际经营地址
    "realCapital":"<String>",									//实收资本（万元）
    "lastIncome":"<String>",									//上年度经营收入
    "lastTax":"<String>",									//上年度税收
    "employees":"<String>",									//员工人数
    "originNumber":"<String>",									//初始入园人数
    "setDate":"<String>",									//成立日期
    "nature":"<String>",									//企业性质
    "certificate":"<File>",									//教师需要上传教师资格证/学生需要上传学生证
    "involved":"<String>",									//企业性质
    "mainBusiness":"<String>",									//主营业务
    "way":"<String>",									//入园方式
    "business":"<String>",									//入园业务
    
    "oldDemand":[],											//注意，这部分信息应当在申请场地之后再进行更新，所以直接传一个空数组即可
    
    "oldShareholder":[{
        "name":"<String>",									//股东姓名或名称
        "stake":"<String>",									//股份比例
        "nature":"<String>",									//股东性质
	},{
        "name":"<String>",
        "stake":"<String>",
        "nature":"<String>",
	}
    	......
    ],
     
    "oldMainPerson":[{
        "name":"<String>",									//姓名
        "born":"<String>",									//出生年月
        "job":"<String>",									//职务
        "school":"<String>",									//毕业学校
        "title":"<String>",									//职称
        "background":"<String>",									//学历
        "professional":"<String>",									//专业
    },{
        "name":"<String>",
        "born":"<String>",
        "job":"<String>",
        "school":"<String>",
        "title":"<String>",
        "background":"<String>",
        "professional":"<String>",
    }
    	......              
    ],

   "oldProject":[{
        "projectBrief":"<String>",									//项目简介
        "advantage":"<String>",									//竞争优势分析
        "market":"<String>",									//市场前景分析
        "energy":"<String>",									//能耗分析
        "pollution":"<String>",									//污染分析
        "noise":"<String>",									//噪音分析
        "others":"<String>",									//其他分析
    },{
        "projectBrief":"<String>",
        "advantage":"<String>",
        "market":"<String>",
        "energy":"<String>",
        "pollution":"<String>",
        "noise":"<String>",
        "others":"<String>",
    }
         ......     
    ],
       
	"oldIntellectual":[{
        "name":"<String>",									//名称
        "kind":"<String>",									//类别
        "applyTime":"<String>",									//申请时间
        "approvalTime":"<String>",									//批准时间
        "intellectualFile":"<File>",								//知识产权证书等扫描文件
    },{
        "name":"<String>",
        "kind":"<String>",
        "applyTime":"<String>",
        "approvalTime":"<String>",
        "intellectualFile":"<File>",
    }
		......
	],

    "oldFunding":[{
    	"name":"<String>",								//项目奖项名称
        "level":"<String>",								//级别
        "time":"<String>",								//时间
        "grants":"<String>",								//获得政府资助金额
        "award":"<String>",								//颁奖部门/时间
    },{
    	"name":"<String>",
        "level":"<String>",
        "time":"<String>",
        "grants":"<String>",
        "award":"<String>",
    }
        ......
    ],
    
    "cooperation":"<String>",								//以往和桂电的合作情况
    "suggestion":"<String>",								//科技园意见
    "note":"<String>"    								//备注
}
```



**返回值：**

请求成功：

~~~json
{
    "data":{
        "id":"<String>",					//表示迁入和独立企业唯一ID
    },
    "code":0,
    "msg":"success"
}
~~~

## 信息状态及展示

**简要描述：**

展示所有已填信息，显示授权状态

**请求URL：**

- `/api/oldEnterprise`

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
	"data":[{
        "registerAddress":"<String>",									//注册地址
	    "license":"<File>",															//新的营业执照上传
	    "registerCapital":"<String>",										//注册资本（万元）
	    "realAddress":"<String>",											//实际经营地址
	    "realCapital":"<String>",											//实收资本（万元）
	    "lastIncome":"<String>",											//上年度经营收入
	    "lastTax":"<String>",													//上年度税收
	    "employees":"<String>",												//员工人数
	    "originNumber":"<String>",										//初始入园人数
	    "setDate":"<String>",												//成立日期
	    "nature":"<String>",													//企业性质
	    "certificate":"<File>",													//教师需要上传教师资格证/学生需要上传学生证
	    "involved":"<String>",												//企业性质
	    "mainBusiness":"<String>",										//主营业务
	    "way":"<String>",														//入园方式
	    "business":"<String>",												//入园业务
	    "oldDemand":[{
	        "leaseArea":"<String>",											//租赁面积（平方米）
	        "position":"<String>",											//位置需求
	        "lease":"<String>",												//租期（年）
	        "floor":"<String>",												//楼层需求
	        "electric":"<String>",											//电力需求
	        "water":"<String>",												//给排水需求
	        "web":"<String>",												//网络需求
	        "others":"<String>",											//其他需求
	    },{
	        "leaseArea":"<String>",
	        "position":"<String>",
	        "lease":"<String>",
	        "floor":"<String>",
	        "electric":"<String>",
	        "water":"<String>",
	        "web":"<String>",
	        "others":"<String>",
	    }
	    	 ......
	    ],
	    
	    "oldShareholder":[{
	        "name":"<String>",												//股东姓名或名称
	        "stake":"<String>",												//股份比例
	        "nature":"<String>",											//股东性质
		},{
	        "name":"<String>",
	        "stake":"<String>",
	        "nature":"<String>",
		}
	    	......
	    ],
	     
	    "oldMainPerson":[{
	        "name":"<String>",												//姓名
	        "born":"<String>",												//出生年月
	        "job":"<String>",												//职务
	        "school":"<String>",										//毕业学校
	        "title":"<String>",												//职称
	        "background":"<String>",									//学历
	        "professional":"<String>",									//专业
	    },{
	        "name":"<String>",
	        "born":"<String>",
	        "job":"<String>",
	        "school":"<String>",
	        "title":"<String>",
	        "background":"<String>",
	        "professional":"<String>",
	    }
	    	......              
	    ],
	
	   "oldProject":[{
	        "projectBrief":"<String>",									//项目简介
	        "advantage":"<String>",									//竞争优势分析
	        "market":"<String>",										//市场前景分析
	        "energy":"<String>",									//能耗分析
	        "pollution":"<String>",									//污染分析
	        "noise":"<String>",											//噪音分析
	        "others":"<String>",									//其他分析
	    },{
	        "projectBrief":"<String>",
	        "advantage":"<String>",
	        "market":"<String>",
	        "energy":"<String>",
	        "pollution":"<String>",
	        "noise":"<String>",
	        "others":"<String>",
	    }
	         ......     
	    ],
	       
		"oldIntellectual":[{
	        "name":"<String>",												//名称
	        "kind":"<String>",												//类别
	        "applyTime":"<String>",										//申请时间
	        "approvalTime":"<String>",									//批准时间
	        "intellectualFile":"<File>",									//知识产权证书等扫描文件
	    },{
	        "name":"<String>",
	        "kind":"<String>",
	        "applyTime":"<String>",
	        "approvalTime":"<String>",
	        "intellectualFile":"<File>",
	    }
			......
		],
	
	    "oldFunding":[{
	    	"name":"<String>",								//项目奖项名称
	        "level":"<String>",								//级别
	        "time":"<String>",								//时间
	        "grants":"<String>",								//获得政府资助金额
	        "award":"<String>",								//颁奖部门/时间
	    },{
	    	"name":"<String>",
	        "level":"<String>",
	        "time":"<String>",
	        "grants":"<String>",
	        "award":"<String>",
	    }
	        ......
	    ],
            
	    "cooperation":"<String>",								//以往和桂电的合作情况
	    "suggestion":"<String>",								//科技园意见
	    "note":"<String>",    								//备注
	    "state":"<String>"									//授权状态
	    }
        ......   
      ],
	  "code":0,
	  "msg":"success"
}   
~~~

## 缴费

**简要描述：**众创空间场地缴费

**请求URL：**

- `/api/fee`

**请求方式：**

- POST（带token）

**参数：**



**返回值：**



------



# 新成立企业或非独立注册企业（new）

## 数据表

### 入园申请表

##### 表名：new（主表）

|      字段名       |  类型  |                    说明                    |
| :---------------: | :----: | :----------------------------------------: |
|    credit_code    | String |        统一社会信用代码（18位字符）        |
| organization_code | String |          组织机构代码企业注册密码          |
|     password      | String |                    密码                    |
|       name        | String |               新设立企业名称               |
|      picture      |  File  |            提供名称预核准通知书            |
|     represent     | String |                 企业负责人                 |
|  represent_card   |  File  |              提供身份证复印件              |
|  represent_phone  | String |             企业负责人联系电话             |
|  represent_email  | String |             企业负责人邮箱地址             |
|       agent       | String |                   经办人                   |
|    agent_phone    | String |               经办人联系电话               |
|    agent_email    | String |               经办人邮箱地址               |
| register_capital  | String |                 拟注册资本                 |
|   real_capital    | String |                实际募集资本                |
|   origin_number   | String |                初始员工人数                |
|   register_time   | String |                预计注册日期                |
|      nature       | String |               拟注册企业性质               |
|    certificate    |  File  | 教室需要上传教师资格证/学生需要上传学生证  |
|     involved      | String |                  所属行业                  |
|   main_business   | String |                  主营业务                  |
|     business      | String |                  入园业务                  |
|    new_demand     | String |    园区场地租赁需求  -->到 newDemand 表    |
|  new_shareholder  | String |      股东构成  -->到 newShareholder表      |
|  new_mainperson   | String |    主要人员介绍  -->到 newMainPerson表     |
|    new_project    | String | 入园项目简要介绍和分析  -->到 newProject表 |
| new_intellectual  | String |   知识产权情况  -->到 newIntellectual表    |
|    suggestion     | String |                 科技园意见                 |
|       note        | String |                    备注                    |
|    submit_time    | String |                  提交时间                  |
|       room        | String |                   房间号                   |
|     inApplyId     | String |    众创空间入驻申请书 -->到 InApply 表     |
|    outApplyId     | String |     科技园退租申请书 -->到 OutApply 表     |
|                   |        |                                            |



##### 表名：new_demand （园区场地租赁需求）

|    字段名     |  类型  |        说明        |
| :-----------: | :----: | :----------------: |
| new_demand_id | String |        UUID        |
|  lease_area   | String | 租赁面积（平方米） |
|   position    | String |      位置需求      |
|     lease     | String |     租期（年）     |
|     floor     | String |      楼层需求      |
|   electric    | String |      电力需求      |
|     water     | String |     给排水需求     |
|      web      | String |      网络需求      |
|    others     | String |      其他需求      |

##### 表名：new_shareholder（股东构成）

| 字段名 |  类型  |      说明      |
| :----: | :----: | :------------: |
|   id   | String |      UUID      |
|  name  | String | 股东姓名或名称 |
| stake  | String |    股份比例    |
| nature | String |    股东性质    |

##### 表名：new_mainperson（主要人员介绍）

|       字段名       |  类型  |   说明   |
| :----------------: | :----: | :------: |
| new_shareholder_id | String |   UUID   |
|        name        | String |   姓名   |
|        born        | String | 出生年月 |
|        job         | String |   职务   |
|       school       | String | 毕业学校 |
|       title        | String |   职称   |
|     background     | String |   学历   |
|    professional    | String |   专业   |

##### 表名：new_project（入园项目简要介绍和分析）

|     字段名     |  类型  |     说明     |
| :------------: | :----: | :----------: |
| new_project_id | String |     UUID     |
| project_brief  | String |   项目简介   |
|   advantage    | String | 竞争优势分析 |
|     market     | String | 市场前景分析 |
|     energy     | String |   能耗分析   |
|   pollution    | String |   污染情况   |
|     noise      | String |   噪音情况   |
|     others     | String |   其他情况   |

##### 表名：new_intellectual（知识产权情况）

|       字段名        |  类型  |          说明          |
| :-----------------: | :----: | :--------------------: |
| new_intellectual_id | String |          UUID          |
|        name         | String |          名称          |
|        kind         | String |          类别          |
|     apply_time      | String |        申请时间        |
|    approval_time    | String |        批准时间        |
|  intellectual_file  |  File  | 知识产权证书等扫描文件 |



## 详细信息填写并提交

**简要描述：**

部分字段在注册是已经填写，所以只需要更新表中数据

**请求URL：**

- `/api/newEnterprise`

**请求方式：**

- PUT（带token）

**参数：**

```json
{
    "registerCapital":"<String>",							//拟注册资本（万元）
    "realCapital":"<String>",									//实际募集资本（万元）
    "originNumber":"<String>",								//初始入园人数
    "registerTime":"<String>",									//预计注册日期
    "nature":"<String>",									//企业性质
    "certificate":"<File>",									//教师需要上传教师资格证/学生需要上传学生证
    "involved":"<String>",									//企业性质
    "mainBusiness":"<String>",									//主营业务
    "way":"<String>",									//入园方式
    "business":"<String>",									//入园业务
    "newDemand":[],
    
    "newShareholder":[{
        "name":"<String>",									//股东姓名或名称
        "stake":"<String>",									//股份比例
        "nature":"<String>",									//股东性质
	},{
        "name":"<String>",
        "stake":"<String>",
        "nature":"<String>",
	}
    	......
    ],
     
    "newMainPerson":[{
        "name":"<String>",									//姓名
        "born":"<String>",									//出生年月
        "job":"<String>",									//职务
        "school":"<String>",									//毕业学校
        "title":"<String>",									//职称
        "background":"<String>",									//学历
        "professional":"<String>",									//专业
    },{
        "name":"<String>",
        "born":"<String>",
        "job":"<String>",
        "school":"<String>",
        "title":"<String>",
        "background":"<String>",
        "professional":"<String>",
    }
    	......              
    ],

   "newProject":[{
        "projectBrief":"<String>",									//项目简介
        "advantage":"<String>",									//竞争优势分析
        "market":"<String>",									//市场前景分析
        "energy":"<String>",									//能耗分析
        "pollution":"<String>",									//污染分析
        "noise":"<String>",									//噪音分析
        "others":"<String>",									//其他分析
    },{
        "projectBrief":"<String>",
        "advantage":"<String>",
        "market":"<String>",
        "energy":"<String>",
        "pollution":"<String>",
        "noise":"<String>",
        "others":"<String>",
    }
         ......     
    ],
       
	"newIntellectual":[{
        "name":"<String>",									//名称
        "kind":"<String>",									//类别
        "applyTime":"<String>",									//申请时间
        "approvalTime":"<String>",									//批准时间
        "intellectualFile":"<File>",								//知识产权证书等扫描文件
    },{
        "name":"<String>",
        "kind":"<String>",
        "applyTime":"<String>",
        "approvalTime":"<String>",
        "intellectualFile":"<File>",
    }
		......
	],
    
    "suggestion":"<String>",								//科技园意见
    "note":"<String>"    								//备注
}
```



**返回值：**

请求成功：

~~~json
{
    "data":{
        "id":"<String>",					//表示新成立企业或非独立注册企业唯一ID
    },
    "code":0,
    "msg":"success"
}
~~~



## 信息状态及展示

**简要描述：**

展示所有已填信息，显示授权状态

**请求URL：**

- `/api/newEnterprise`

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
	    "data":[{
	        "registerCapital":"<String>",							//拟注册资本（万元）
		    "realCapital":"<String>",									//实际募集资本（万元）
		    "originNumber":"<String>",								//初始入园人数
		    "registerTime":"<String>",									//预计注册日期
		    "nature":"<String>",									//企业性质
		    "certificate":"<File>",									//教师需要上传教师资格证/学生需要上传学生证
		    "involved":"<String>",									//企业性质
		    "mainBusiness":"<String>",									//主营业务
		    "way":"<String>",									//入园方式
		    "business":"<String>",									//入园业务
		    
		    "newDemand":[{
		        "leaseArea":"<String>",									//租赁面积（平方米）
		        "position":"<String>",									//位置需求
		        "lease":"<String>",									//租期（年）
		        "floor":"<String>",									//楼层需求
		        "electric":"<String>",									//电力需求
		        "water":"<String>",									//给排水需求
		        "web":"<String>",									//网络需求
		        "others":"<String>",									//其他需求
		    },{
		        "leaseArea":"<String>",
		        "position":"<String>",
		        "lease":"<String>",
		        "floor":"<String>",
		        "electric":"<String>",
		        "water":"<String>",
		        "web":"<String>",
		        "others":"<String>",
		    }
		    	 ......
		    ],
		    
		    "newShareholder":[{
		        "name":"<String>",									//股东姓名或名称
		        "stake":"<String>",									//股份比例
		        "nature":"<String>",									//股东性质
			},{
		        "name":"<String>",
		        "stake":"<String>",
		        "nature":"<String>",
			}
		    	......
		    ],
		     
		    "newMainPerson":[{
		        "name":"<String>",									//姓名
		        "born":"<String>",									//出生年月
		        "job":"<String>",									//职务
		        "school":"<String>",									//毕业学校
		        "title":"<String>",									//职称
		        "background":"<String>",									//学历
		        "professional":"<String>",									//专业
		    },{
		        "name":"<String>",
		        "born":"<String>",
		        "job":"<String>",
		        "school":"<String>",
		        "title":"<String>",
		        "background":"<String>",
		        "professional":"<String>",
		    }
		    	......              
		    ],
		
		   "newProject":[{
		        "projectBrief":"<String>",									//项目简介
		        "advantage":"<String>",									//竞争优势分析
		        "market":"<String>",									//市场前景分析
		        "energy":"<String>",									//能耗分析
		        "pollution":"<String>",									//污染分析
		        "noise":"<String>",									//噪音分析
       	 "others":"<String>",									//其他分析
		    },{
		        "projectBrief":"<String>",
		        "advantage":"<String>",
		        "market":"<String>",
		        "energy":"<String>",
		        "pollution":"<String>",
		        "noise":"<String>",
		        "others":"<String>",
		    }
		         ......     
		    ],
		       
			"newIntellectual":[{
		        "name":"<String>",									//名称
		        "kind":"<String>",									//类别
		        "applyTime":"<String>",									//申请时间
		        "approvalTime":"<String>",									//批准时间
		        "intellectualFile":"<File>",								//知识产权证书等扫描文件
		    },{
		        "name":"<String>",
		        "kind":"<String>",
		        "applyTime":"<String>",
		        "approvalTime":"<String>",
		        "intellectualFile":"<File>",
		    }
				......
			],
		    
		    "suggestion":"<String>",								//科技园意见
		    "note":"<String>"    								//备注
	    "state":"<String>"
	    }],
	  "code":0,
	  "msg":"success"
}   
~~~

## 缴费

**简要描述：**

**请求URL：**

**请求方式：**

**参数：**

**返回值：**



------



# 众创空间

> 由于众创空间申请和退租不分新旧企业，所以此部分独立

## 数据表

### 众创空间申请表

##### space（主表）

|    字段名    |  类型  |       说明       |
| :----------: | :----: | :--------------: |
|  InApplyId   | String | 众创空间申请表id |
| newInApplyId | String |  项目/创意名称   |
|  applyTime   | String |     申请日期     |
|  teamNumber  | String |     团队人数     |
|   describe   | String |  项目/创意概况   |
|     help     | String |   想获得的帮助   |
|    brief     | String |     项目概括     |

##### space_person（人员表）

|    字段名    |  类型  |       说明       |
| :----------: | :----: | :--------------: |
|  InApplyId   | String | 众创空间申请表id |
|  personName  | String |       姓名       |
|  department  | String |  所在院校/部门   |
|    major     | String |     专业方向     |
| personPhone  | String |     手机号码     |
|   personQq   | String |       QQ号       |
| personWechat | String |      微信号      |
|     note     | String |       备注       |

## 众创空间申请

**简要描述：**众创空间场地申请

**请求URL：**

- `/api/space`

**请求方式：**

- POST（带token）

**参数：**

```json
{          
  "describe": "<String>",             // 注明新/旧企业
  "createName": "<String>",			  // 项目/创意名称
  "applyTime": "<String>",            // 申请日期
  "teamNumber": "<String>",			  // 成员数量
  "Person": [{                         // 主要成员信息
      "personName": "<String>",
      "department": "<String>",
      "major": "<String>",
      "personPhone": "<String>",
      "personQq": "<String>",
      "personWechat": "<String>",
      "note": "<String>"
    },
    ......
  ],
  "brief": "<String>",                // 项目/创意概况
  "help": "<String>"                  // 想获得的帮助
}
```

**返回值：**

```json
{
    "data":{
        "InApplyId":"<String>",					//众创空间企业唯一ID
    },
    "code":0,
    "msg":"success"
}
```



## 众创空间退出

**简要描述：**撤销所申请的众创空间场地

**请求URL：**

- `/api/space`

**请求方式：**

- DELETE（带token）

**参数：**

```json
{
    "InApplyId":"<String>"                  //众创空间企业唯一ID
}
```

**返回值：**

```json
{
    "data":{
        "InApplyId":"<String>",					//众创空间企业唯一ID
    },
    "code":0,
    "msg":"success"
}
```





------

# 导出报表

> 报表不分新旧企业，所以此部分独立

## 导出科技园在孵企业统计表

**简要描述：**

导出科技园在孵企业统计表

**请求URL：**

- `/api/form/statistical`

**请求方式：**

- GET（带token）

**参数**：

```json
{
    "data":{
    	"Code":"<String>",						//这里传统一社会信用代码或组织机构代码企业注册密码
	}
}
```

**返回值：**

~~~json
{
    "data":{
        "file":"<byte[]>",					// 返回一个byte数组
    },
    "code":0,
    "msg":"success"
}
~~~



## 导出科技园在孵企业情况表

**简要描述：**

导出科技园在孵企业情况表

**请求URL：**

- `/api/form/situation`

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
    "data":{
        "file":"<byte[]>",					// 返回一个byte数组
    },
    "code":0,
    "msg":"success"
}
~~~





------



# 管理员（admin）

## 基础操作

### 获取全部迁入和独立注册企业

**简要描述：**

返回部分参数

**请求URL：**

- `/api/admin/all`

**请求方式：**

- GET

**返回值：**

~~~json
{
    "data":[{
        "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    	"organizationCode":"<String>",									//组织机构代码
<<<<<<< HEAD
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",										//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
=======
        "name":"<String>",												//申请入驻企业名称
    	"represent":"<String>",											//法人代表
    	"representPhone":"<String>",									//法人代表联系电话
    	"representEmail":"<String>",									//法人代表邮箱地址
        "administratorAudit":"<String>",								//管理员授权状态
        "position":"<String>",											//位置需求
        "floor":"<String>"												//楼层需求
        "describe":"<String>"											//表示众创空间/科技园的申请
>>>>>>> bef245fdc77bbd206b518da86908ca94fef5e2d7
    },{
        "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    	"organizationCode":"<String>",									//组织机构代码
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",											//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },
           ......
    ],
    "code":0,
    "msg":"success"
}
~~~

### 获取某一个迁入和独立注册企业入园申请

**简要描述：**

获取一个企业的全部信息

**请求URL：**

<<<<<<< HEAD
- `/api/admin/old/{id}`
=======
- `/api/admin/technology/{creditCode}`
>>>>>>> bef245fdc77bbd206b518da86908ca94fef5e2d7

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
	"data":{
	    "registerAddress":"<String>",									//注册地址
	    "license":"<File>",															//新的营业执照上传
	    "registerCapital":"<String>",										//注册资本（万元）
	    "realAddress":"<String>",											//实际经营地址
	    "realCapital":"<String>",											//实收资本（万元）
	    "lastIncome":"<String>",											//上年度经营收入
	    "lastTax":"<String>",													//上年度税收
	    "employees":"<String>",												//员工人数
	    "originNumber":"<String>",										//初始入园人数
	    "setDate":"<String>",												//成立日期
	    "nature":"<String>",													//企业性质
	    "certificate":"<File>",													//教师需要上传教师资格证/学生需要上传学生证
	    "involved":"<String>",												//企业性质
	    "mainBusiness":"<String>",										//主营业务
	    "way":"<String>",														//入园方式
	    "business":"<String>",												//入园业务
	    "Demand":[{
	        "leaseArea":"<String>",											//租赁面积（平方米）
	        "position":"<String>",											//位置需求
	        "lease":"<String>",												//租期（年）
	        "floor":"<String>",												//楼层需求
	        "electric":"<String>",											//电力需求
	        "water":"<String>",												//给排水需求
	        "web":"<String>",												//网络需求
	        "others":"<String>",											//其他需求
	    },{
	        "leaseArea":"<String>",
	        "position":"<String>",
	        "lease":"<String>",
	        "floor":"<String>",
	        "electric":"<String>",
	        "water":"<String>",
	        "web":"<String>",
	        "others":"<String>",
	    }
	    	 ......
	    ],
	    "Shareholder":[{
	        "name":"<String>",												//股东姓名或名称
	        "stake":"<String>",												//股份比例
	        "nature":"<String>",											//股东性质
		},{
	        "name":"<String>",
	        "stake":"<String>",
	        "nature":"<String>",
		}
	    	......
	    ],
	     
	    "MainPerson":[{
	        "name":"<String>",												//姓名
	        "born":"<String>",												//出生年月
	        "job":"<String>",												//职务
	        "school":"<String>",										//毕业学校
	        "title":"<String>",												//职称
	        "background":"<String>",									//学历
	        "professional":"<String>",									//专业
	    },{
	        "name":"<String>",
	        "born":"<String>",
	        "job":"<String>",
	        "school":"<String>",
	        "title":"<String>",
	        "background":"<String>",
	        "professional":"<String>",
	    }
	    	......              
	    ],
	
	   "Project":[{
	        "projectBrief":"<String>",									//项目简介
	        "advantage":"<String>",									//竞争优势分析
	        "market":"<String>",										//市场前景分析
	        "energy":"<String>",									//能耗分析
	        "pollution":"<String>",									//污染分析
	        "noise":"<String>",											//噪音分析
	        "others":"<String>",									//其他分析
	    },{
	        "projectBrief":"<String>",
	        "advantage":"<String>",
	        "market":"<String>",
	        "energy":"<String>",
	        "pollution":"<String>",
	        "noise":"<String>",
	        "others":"<String>",
	    }
	         ......     
	    ],
	       
		"Intellectual":[{
	        "name":"<String>",												//名称
	        "kind":"<String>",												//类别
	        "applyTime":"<String>",										//申请时间
	        "approvalTime":"<String>",									//批准时间
	        "intellectualFile":"<File>",									//知识产权证书等扫描文件
	    },{
	        "name":"<String>",
	        "kind":"<String>",
	        "applyTime":"<String>",
	        "approvalTime":"<String>",
	        "intellectualFile":"<File>",
	    }
			......
		],
	
	    "Funding":[{
	    	"name":"<String>",								//项目奖项名称
	        "level":"<String>",								//级别
	        "time":"<String>",								//时间
	        "grants":"<String>",								//获得政府资助金额
	        "award":"<String>",								//颁奖部门/时间
	    },{
	    	"name":"<String>",
	        "level":"<String>",
	        "time":"<String>",
	        "grants":"<String>",
	        "award":"<String>",
	    }
	        ......
	    ],
            
	    "cooperation":"<String>",								//以往和桂电的合作情况
	    "suggestion":"<String>",								//科技园意见
	    "note":"<String>",    								//备注
	    "state":"<String>"										//状态
        "room":"<String>"									//房间号
	},
	"code":0,
	"msg":"success"
}
~~~



<<<<<<< HEAD
### 删除某一个迁入和独立注册企业入园申请
=======
获取某一个企业众创空间申请

**请求URL：**

- `/api/admin/space/{creditCode}`

**请求方式：**

- GET

**返回值：**

请求成功：

```json
{
    "data":{
        
    },
    "code":0,
    "msg":"suceess"
}
```

### 删除某一个企业入园申请
>>>>>>> bef245fdc77bbd206b518da86908ca94fef5e2d7

**简要描述：**

删除某个企业的详细内容（包括账号）

**请求URL：**

<<<<<<< HEAD
- `/api/admin/old`
=======
- `/api/admin/technology/{creditCode}`
>>>>>>> bef245fdc77bbd206b518da86908ca94fef5e2d7

**请求方式：**

- DELETE

**参数：**

```json
{
    "code":"<String>",											//统一社会信用代码（18位字符）或 组织机构代码
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "creditCode":"<String>",							//企业唯一ID
    },
    "code":0,
    "msg":"suceess"
}
```

请求失败：

~~~json
{
    "data":null,
    "code":-1,
    "msg":"id not exist"
}
~~~



### 获取全部新成立企业或非独立注册企业

**简要描述：**

返回部分参数

**请求URL：**

- `/api/admin/new`

**请求方式：**

- GET

**返回值：**

~~~json
{
    "data":[{
       "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    	"organizationCode":"<String>",									//组织机构代码
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",										//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },{
        "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    	"organizationCode":"<String>",									//组织机构代码
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",											//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },
           ......
    ],
    "code":0,
    "msg":"success"
}
~~~



### 获取某一个新成立企业或非独立注册企业入园申请

**简要描述：**

获取一个企业的全部信息

**请求URL：**

- `/api/admin/old/{id}`

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
	    "data":[{
	        "registerCapital":"<String>",							//拟注册资本（万元）
		    "realCapital":"<String>",									//实际募集资本（万元）
		    "originNumber":"<String>",								//初始入园人数
		    "registerTime":"<String>",									//预计注册日期
		    "nature":"<String>",									//企业性质
		    "certificate":"<File>",									//教师需要上传教师资格证/学生需要上传学生证
		    "involved":"<String>",									//企业性质
		    "mainBusiness":"<String>",									//主营业务
		    "way":"<String>",									//入园方式
		    "business":"<String>",									//入园业务
		    
		    "newDemand":[{
		        "leaseArea":"<String>",									//租赁面积（平方米）
		        "position":"<String>",									//位置需求
		        "lease":"<String>",									//租期（年）
		        "floor":"<String>",									//楼层需求
		        "electric":"<String>",									//电力需求
		        "water":"<String>",									//给排水需求
		        "web":"<String>",									//网络需求
		        "others":"<String>",									//其他需求
		    },{
		        "leaseArea":"<String>",
		        "position":"<String>",
		        "lease":"<String>",
		        "floor":"<String>",
		        "electric":"<String>",
		        "water":"<String>",
		        "web":"<String>",
		        "others":"<String>",
		    }
		    	 ......
		    ],
		    
		    "newShareholder":[{
		        "name":"<String>",									//股东姓名或名称
		        "stake":"<String>",									//股份比例
		        "nature":"<String>",									//股东性质
			},{
		        "name":"<String>",
		        "stake":"<String>",
		        "nature":"<String>",
			}
		    	......
		    ],
		     
		    "newMainPerson":[{
		        "name":"<String>",									//姓名
		        "born":"<String>",									//出生年月
		        "job":"<String>",									//职务
		        "school":"<String>",									//毕业学校
		        "title":"<String>",									//职称
		        "background":"<String>",									//学历
		        "professional":"<String>",									//专业
		    },{
		        "name":"<String>",
		        "born":"<String>",
		        "job":"<String>",
		        "school":"<String>",
		        "title":"<String>",
		        "background":"<String>",
		        "professional":"<String>",
		    }
		    	......              
		    ],
		
		   "newProject":[{
		        "projectBrief":"<String>",									//项目简介
		        "advantage":"<String>",									//竞争优势分析
		        "market":"<String>",									//市场前景分析
		        "energy":"<String>",									//能耗分析
		        "pollution":"<String>",									//污染分析
		        "noise":"<String>",									//噪音分析
       	 "others":"<String>",									//其他分析
		    },{
		        "projectBrief":"<String>",
		        "advantage":"<String>",
		        "market":"<String>",
		        "energy":"<String>",
		        "pollution":"<String>",
		        "noise":"<String>",
		        "others":"<String>",
		    }
		         ......     
		    ],
		       
			"newIntellectual":[{
		        "name":"<String>",									//名称
		        "kind":"<String>",									//类别
		        "applyTime":"<String>",									//申请时间
		        "approvalTime":"<String>",									//批准时间
		        "intellectualFile":"<File>",								//知识产权证书等扫描文件
		    },{
		        "name":"<String>",
		        "kind":"<String>",
		        "applyTime":"<String>",
		        "approvalTime":"<String>",
		        "intellectualFile":"<File>",
		    }
				......
			],
		    
		    "suggestion":"<String>",								//科技园意见
		    "note":"<String>"    								//备注
	    	"state":"<String>"										//状态
        	"room":"<String>"									//房间号                
	    }],
	  "code":0,
	  "msg":"success"
}   
~~~



### 删除某一个新成立企业或非独立注册企业入园申请

**简要描述：**

删除某个企业的详细内容（包括账号）

**请求URL：**

<<<<<<< HEAD
- `/api/admin/new`
=======
- `/api/admin/space/{creditCode}`
>>>>>>> bef245fdc77bbd206b518da86908ca94fef5e2d7

**请求方式：**

- DELETE

**返回值：**

请求成功：

```json
{
    "data":{
        "creditCode":"<String>",							//企业唯一ID
    },
    "code":0,
    "msg":"suceess"
}
```

请求失败：

~~~json
{
    "data":null,
    "code":-1,
    "msg":"id not exist"
}
~~~



### 缴费设置



------



## 授权类操作

### 入园申请审核授权

**简要描述：**

科技园入驻审核

**请求URL：**

- `/api/admin/technology`

**请求方式：**

- POST

**参数：**

```json
{
    "creditCode":"<String>"
}
```

**返回值：**

```json
{
    "data":{
        "creditCode":"<String>"
    },
    "code":0,
    "msg":"success"
}
```



### 企业信息审核

**简要描述：**

**请求URL：**

**请求方式：**

**参数：**

**返回值：**



------



## 房间类操作

### 获取所有房间状况

**简要描述：**

**请求URL：**

**请求方式：**

**参数：**

**返回值：**

### 新增房间

**简要描述：**

**请求URL：**

**请求方式：**

**参数：**

**返回值：**

### 删除已有房间

**简要描述：**

**请求URL：**

**请求方式：**

**参数：**

**返回值：**

### 众创空间分配

**简要描述：**

**请求URL：**

**请求方式：**

**参数：**

**返回值：**



------



# 领导（leader）

## **数据表：**



## 审核授权

**简要描述：**

审核授权

**请求URL：**

- `/api/authorization`

**请求方式：**

- POST(带token)

**参数**：

```json
"data"：{
    ""
}
```



**返回值：**





## 获取全部迁入和独立注册企业

**简要描述：**

返回部分参数

**请求URL：**

- `/api/getold`

**请求方式：**

- GET(带token)

**返回值：**

~~~json
{
    "data":[{
        "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    	"organizationCode":"<String>",									//组织机构代码
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",										//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },{
        "creditCode":"<String>",                                                //统一社会信用代码（18位字符）
    	"organizationCode":"<String>",									//组织机构代码
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",											//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },
           ......
    ],
    "code":0,
    "msg":"success"
}
~~~



## 获取某一个迁入和独立注册企业

**简要描述：**

获取一个企业的全部信息

**请求URL：**

- `/api/getold/{id}`

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
	"data":[{
	    "registerAddress":"<String>",									//注册地址
	    "license":"<File>",															//新的营业执照上传
	    "registerCapital":"<String>",										//注册资本（万元）
	    "realAddress":"<String>",											//实际经营地址
	    "realCapital":"<String>",											//实收资本（万元）
	    "lastIncome":"<String>",											//上年度经营收入
	    "lastTax":"<String>",													//上年度税收
	    "employees":"<String>",												//员工人数
	    "originNumber":"<String>",										//初始入园人数
	    "setDate":"<String>",												//成立日期
	    "nature":"<String>",													//企业性质
	    "certificate":"<File>",									//教师需要上传教师资格证/学生需要上传学生证
	    "involved":"<String>",												//企业性质
	    "mainBusiness":"<String>",										//主营业务
	    "way":"<String>",														//入园方式
	    "business":"<String>",												//入园业务
	    
	    "oldDemand":[{
	        "leaseArea":"<String>",											//租赁面积（平方米）
	        "position":"<String>",											//位置需求
	        "lease":"<String>",												//租期（年）
	        "floor":"<String>",												//楼层需求
	        "electric":"<String>",											//电力需求
	        "water":"<String>",												//给排水需求
	        "web":"<String>",												//网络需求
	        "others":"<String>",											//其他需求
	    },{
	        "leaseArea":"<String>",
	        "position":"<String>",
	        "lease":"<String>",
	        "floor":"<String>",
	        "electric":"<String>",
	        "water":"<String>",
	        "web":"<String>",
	        "others":"<String>",
	    }
	    	 ......
	    ],
	    
	    "oldShareholder":[{
	        "name":"<String>",												//股东姓名或名称
	        "stake":"<String>",												//股份比例
	        "nature":"<String>",											//股东性质
		},{
	        "name":"<String>",
	        "stake":"<String>",
	        "nature":"<String>",
		}
	    	......
	    ],
	     
	    "oldMainPerson":[{
	        "name":"<String>",												//姓名
	        "born":"<String>",												//出生年月
	        "job":"<String>",												//职务
	        "school":"<String>",										//毕业学校
	        "title":"<String>",												//职称
	        "background":"<String>",									//学历
	        "professional":"<String>",									//专业
	    },{
	        "name":"<String>",
	        "born":"<String>",
	        "job":"<String>",
	        "school":"<String>",
	        "title":"<String>",
	        "background":"<String>",
	        "professional":"<String>",
	    }
	    	......              
	    ],
	
	   "oldProject":[{
	        "projectBrief":"<String>",									//项目简介
	        "advantage":"<String>",									//竞争优势分析
	        "market":"<String>",										//市场前景分析
	        "energy":"<String>",									//能耗分析
	        "pollution":"<String>",									//污染分析
	        "noise":"<String>",											//噪音分析
	        "others":"<String>",									//其他分析
	    },{
	        "projectBrief":"<String>",
	        "advantage":"<String>",
	        "market":"<String>",
	        "energy":"<String>",
	        "pollution":"<String>",
	        "noise":"<String>",
	        "others":"<String>",
	    }
	         ......     
	    ],
	       
		"oldIntellectual":[{
	        "name":"<String>",												//名称
	        "kind":"<String>",												//类别
	        "applyTime":"<String>",										//申请时间
	        "approvalTime":"<String>",									//批准时间
	        "intellectualFile":"<File>",									//知识产权证书等扫描文件
	    },{
	        "name":"<String>",
	        "kind":"<String>",
	        "applyTime":"<String>",
	        "approvalTime":"<String>",
	        "intellectualFile":"<File>",
	    }
			......
		],
	
	    "oldFunding":[{
	    	"name":"<String>",								//项目奖项名称
	        "level":"<String>",								//级别
	        "time":"<String>",								//时间
	        "grants":"<String>",								//获得政府资助金额
	        "award":"<String>",								//颁奖部门/时间
	    },{
	    	"name":"<String>",
	        "level":"<String>",
	        "time":"<String>",
	        "grants":"<String>",
	        "award":"<String>",
	    }
	        ......
	    ],
            
	    "cooperation":"<String>",								//以往和桂电的合作情况
	    "suggestion":"<String>",								//科技园意见
	    "note":"<String>",    								//备注
	    "state":"<String>"										//状态
        "room":"<String>"									//房间号
	    }],
	  "code":0,
	  "msg":"success"
}
~~~

## 获取全部新成立企业或非独立注册企业

**简要描述：**

返回部分参数

**请求URL：**

- `/api/getnew`

**请求方式：**

- GET(带token)

**返回值：**

~~~json
{
    "data":[{
        "id":"<String>",
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",										//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },{
        "id":"<String>",
        "name":"<String>",														//申请入驻企业名称
    	"represent":"<String>",												  //法人代表
    	"representPhone":"<String>",									 //法人代表联系电话
    	"representEmail":"<String>",											//法人代表邮箱地址
        "state":"<String>",															//授权状态
        "room":"<String>",														//房间号
    },
           ......
    ],
    "code":0,
    "msg":"success"
}
~~~



## 获取某一个新成立企业或非独立注册企业

**简要描述：**

获取一个企业的全部信息

**请求URL：**

- `/api/getold/{id}`

**请求方式：**

- GET（带token）

**返回值：**

~~~json
{
	    "data":[{
	        "registerCapital":"<String>",							//拟注册资本（万元）
		    "realCapital":"<String>",									//实际募集资本（万元）
		    "originNumber":"<String>",								//初始入园人数
		    "registerTime":"<String>",									//预计注册日期
		    "nature":"<String>",									//企业性质
		    "certificate":"<File>",									//教师需要上传教师资格证/学生需要上传学生证
		    "involved":"<String>",									//企业性质
		    "mainBusiness":"<String>",									//主营业务
		    "way":"<String>",									//入园方式
		    "business":"<String>",									//入园业务
		    
		    "newDemand":[{
		        "leaseArea":"<String>",									//租赁面积（平方米）
		        "position":"<String>",									//位置需求
		        "lease":"<String>",									//租期（年）
		        "floor":"<String>",									//楼层需求
		        "electric":"<String>",									//电力需求
		        "water":"<String>",									//给排水需求
		        "web":"<String>",									//网络需求
		        "others":"<String>",									//其他需求
		    },{
		        "leaseArea":"<String>",
		        "position":"<String>",
		        "lease":"<String>",
		        "floor":"<String>",
		        "electric":"<String>",
		        "water":"<String>",
		        "web":"<String>",
		        "others":"<String>",
		    }
		    	 ......
		    ],
		    
		    "newShareholder":[{
		        "name":"<String>",									//股东姓名或名称
		        "stake":"<String>",									//股份比例
		        "nature":"<String>",									//股东性质
			},{
		        "name":"<String>",
		        "stake":"<String>",
		        "nature":"<String>",
			}
		    	......
		    ],
		     
		    "newMainPerson":[{
		        "name":"<String>",									//姓名
		        "born":"<String>",									//出生年月
		        "job":"<String>",									//职务
		        "school":"<String>",									//毕业学校
		        "title":"<String>",									//职称
		        "background":"<String>",									//学历
		        "professional":"<String>",									//专业
		    },{
		        "name":"<String>",
		        "born":"<String>",
		        "job":"<String>",
		        "school":"<String>",
		        "title":"<String>",
		        "background":"<String>",
		        "professional":"<String>",
		    }
		    	......              
		    ],
		
		   "newProject":[{
		        "projectBrief":"<String>",									//项目简介
		        "advantage":"<String>",									//竞争优势分析
		        "market":"<String>",									//市场前景分析
		        "energy":"<String>",									//能耗分析
		        "pollution":"<String>",									//污染分析
		        "noise":"<String>",									//噪音分析
       	 "others":"<String>",									//其他分析
		    },{
		        "projectBrief":"<String>",
		        "advantage":"<String>",
		        "market":"<String>",
		        "energy":"<String>",
		        "pollution":"<String>",
		        "noise":"<String>",
		        "others":"<String>",
		    }
		         ......     
		    ],
		       
			"newIntellectual":[{
		        "name":"<String>",									//名称
		        "kind":"<String>",									//类别
		        "applyTime":"<String>",									//申请时间
		        "approvalTime":"<String>",									//批准时间
		        "intellectualFile":"<File>",								//知识产权证书等扫描文件
		    },{
		        "name":"<String>",
		        "kind":"<String>",
		        "applyTime":"<String>",
		        "approvalTime":"<String>",
		        "intellectualFile":"<File>",
		    }
				......
			],
		    
		    "suggestion":"<String>",								//科技园意见
		    "note":"<String>"    								//备注
	    	"state":"<String>"										//状态
        	"room":"<String>"									//房间号                
	    }],
	  "code":0,
	  "msg":"success"
}   
~~~

# 场地

## 数据表

### 表名：inapply（众创空间入驻申请）

|     字段名     |  类型  |                         说明                         |
| :------------: | :----: | :--------------------------------------------------: |
|       id       | String |                                                      |
| new_inapply_id | String |                         UUID                         |
|  create_name   | String |                        创意名                        |
|   apply_time   | String |                 申请日期（由前端给）                 |
|  team_number   | String |                       团队人数                       |
| new_person_id  | String |                 UUID -> 到person 表                  |
|     brief      | String |                    项目/创意概括                     |
|      help      | String | 您想获得的帮助（如创业辅导、投融资服务、市场推广等） |



### 表名：person

|    字段名     |  类型  |     说明      |
| :-----------: | :----: | :-----------: |
|  person_name  | String |     姓名      |
|  department   | String | 所在院校/部门 |
|     major     | String |   专业方向    |
| person_phone  | String |   电话号码    |
|   person_qq   | String |     QQ号      |
| person_wechat | String |    微信号     |
|     note      | String |     备注      |
