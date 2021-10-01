<h1>API</h1>

[TOC]

## 登陆（login）

**简要描述:**

管理员账号登陆

**请求URL:**

- `/api/login`

**请求方式:**

- POST

**参数:**

管理员账号唯一

```json
{
    "name":"<string>",      // 用户名
    "password":"<string>"   // 密码
} 
```

**返回值:**

请求成功会返回一个唯一授权凭证（Bearer Token）：

```json
{
	"data":{
		"token":"<string>",
    },
    "code":0,
    "msg":"success"
}
```

> token保存时间待定

请求失败：

```json
{
	"data":null,
    "code":-1，
    "msg":"The user name or password is incorrect"
}
```



## 企业/团队基本情况(enterprise)

#### 获取某个企业/团队的详细情况

**简要描述：**

获取某个企业/团队的详细情况，应当先请求全部企业基本情况后获取该企业id字段

**请求URL：**

id为表示企业/团队的唯一id

- `/api/enterprise/{enterpriseId}`

**请求方式：**

- GET（带token）

**返回值：**

请求成功：

```json
{
    "data":{
        "enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
        "teamName":"<String>",					//企业/团队名称
        "head":"<String>",						//企业负责人
        "phone":"<String>",						//联系方式
        "joinTime":"<String>",					//入驻众创空间时间
        "teamNumber":"<String>",				//人数
        "characteristic":"<String>",			//主要负责人创业特征
        "kind":"<String>",						//行业类别
        "field":"<String>",						//技术领域
        "achievements":"<String>",				//获奖成果
        "scope":"<String>",						//经营范围
        "income":"<String>",					//总收入
        "tax":"<String>",						//上缴税额
        "preferentialTax":"<Boolean>",			//享受税收优惠政策 (是：true 否：false)
        "taxFree":"<String>",					//免税金额 (是：具体金额 否：null)
        "support":"<Boolean>",					//获得财政资金支持
        "supportAmount":"<String>",				//支持金额 
        "riskInvestment":"<Boolean>",			//获天使或风险投资额
        "investmentAmount":"<String>",			//投融资总额
        "cooperation":"<Boolean>",				//是否参与校企合作
        "projectName":"<String>",				//合作项目名称
        "projectAmount":"<String>",				//申报项目金额
        "highTec":"<Boolean>",					//是否高新技术企业
        "tecSme":"<Boolean>"					//是否是科技型中小企业
    },
    "code":0，
    "msg":"success",
}
```

请求失败的返回结果：

```json
{
    "data":null,
    "code":-1,
    "msg":"EnterpriseId doesn't exist"
}
```



#### 获取全部企业/团队的基本情况

**简要描述：**

获取全部企业/团队的基本情况

**请求URL：**

- `/api/enterprise`

**请求方式：**

- GET（带token）

**返回值：**

```json
{
    "data":[{
        "enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
        "teamName":"<String>",					//企业/团队名称
        "head":"<String>",						//企业负责人
        "phone":"<String>",						//联系方式
        "joinTime":"<String>",					//入驻众创空间时间
        "teamNumber":"<String>",				//人数
    },{
        "enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
        "teamName":"<String>",					//企业/团队名称
        "head":"<String>",						//企业负责人
        "phone":"<String>",						//联系方式
        "joinTime":"<String>",					//入驻众创空间时间
        "teamNumber":"<String>",				//人数
    },
    	......
    ],
    "code":0，
    "msg":"success",
}
```



#### 增加某个企业/团队

**简要描述：**

增加某个企业/团队，并提交详细情况表单

**请求URL：**

- `/api/enterprise`

**请求方式：**

- POST（带token）

**参数：**

```json
{
	"teamName":"<String>",					//企业/团队名称
    "head":"<String>",						//企业负责人
    "phone":"<String>",						//联系方式
    "joinTime":"<String>",					//入驻众创空间时间
    "teamNumber":"<String>",				//人数
    "characteristic":"<String>",			//主要负责人创业特征
    "kind":"<String>",						//行业类别
    "field":"<String>",						//技术领域
    "achievements":"<String>",				//获奖成果
    "scope":"<String>",						//经营范围
    "income":"<String>",					//总收入
    "tax":"<String>",						//上缴税额
    "preferentialTax":"<Boolean>",			//享受税收优惠政策 (是：true 否：false)
    "taxFree":"<String>",					//免税金额 (是：具体金额 否：null)
    "support":"<Boolean>",					//获得财政资金支持
    "supportAmount":"<String>",			//支持金额 
    "riskInvestment":"<Boolean>",			//获天使或风险投资额
    "investmentAmount":"<String>",			//投融资总额
    "cooperation":"<Boolean>",				//是否参与校企合作
    "projectName":"<String>",				//合作项目名称
    "projectAmount":"<String>",				//申报项目金额
    "highTec":"<Boolean>",					//是否高新技术企业
    "tecSme":"<Boolean>"					//是否是科技型中小企业
}
```

**返回：**

请求成功：

```json
{
	"data":{
		"enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
	},
    "code":0,
    "msg":"succcess"
}
```



#### 修改某个企业/团队详细情况

**简要描述：**

增加某个企业/团队，并提交详细情况表单

**请求URL：**

- `/api/enterprise`

**请求方式：**

- PUT（带token）

**参数：**

```json
{
    "enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
    "teamName":"<String>",					//企业/团队名称
    "head":"<String>",						//企业负责人
    "phone":"<String>",						//联系方式
    "joinTime":"<String>",					//入驻众创空间时间
    "teamNumber":"<String>",				//人数
    "characteristic":"<String>",			//主要负责人创业特征
    "kind":"<String>",						//行业类别
    "field":"<String>",						//技术领域
    "achievements":"<String>",				//获奖成果
    "scope":"<String>",						//经营范围
    "income":"<String>",					//总收入
    "tax":"<String>",						//上缴税额
    "preferentialTax":"<Boolean>",			//享受税收优惠政策 (是：true 否：false)
    "taxFree":"<String>",					//免税金额 (是：具体金额 否：null)
    "support":"<Boolean>",					//获得财政资金支持
    "supportAmount":"<String>",				//支持金额 
    "riskInvestment":"<Boolean>",			//获天使或风险投资额
    "investmentAmount":"<String>",			//投融资总额
    "cooperation":"<Boolean>",				//是否参与校企合作
    "projectName":"<String>",				//合作项目名称
    "projectAount":"<String>",				//申报项目金额
    "highTec":"<Boolean>",					//是否高新技术企业
    "tecSme":"<Boolean>"					//是否是科技型中小企业
}
```

**返回：**

请求成功：

```json
{
	"data":{
		"enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
	},
    "code":0,
    "msg":"succcess"
}
```

请求失败：

```
{
	"data":null,
    "code":-1,
    "msg":"enterpriseId doesn't exist"
}
```



#### 删除某个企业/团队

**简要描述：**

删除某个企业/团队

**请求URL：**

- `/api/enterprise`

**请求方式：**

- DELETE（带token）

**参数**：

```json
{
    "enterpriseId":"<string>"
}
```

**返回：**

请求成功：

```json
{
	"data":{
		"enterpriseId":"<string>",              //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
	},
    "code":0,
    "msg":"succcess"
}
```

请求失败：

```json
{
	"data":null,
    "code":-1,
    "msg":"enterpriseId doesn't exist"
}
```



## 入驻企业/团队从业人员情况（employee）

#### 获取某个企业/团队从业人员详细情况

**简要描述：**

获取某个企业/团队从业人员详细情况

**请求URL：**

- `/api/employee/{enterpriseId}`

**请求方式：**

- GET（带token）

**返回值：**

请求成功：

```json
{
	"data":{
        "employee":{
			"employeeId":"<string>",		//企业/团队从业情况表唯一Id
            "employees":"<int>",				//在孵企业/团队从业人员
            "doctor":"<int>",				//博士
            "master":"<int>",				//硕士
            "graduate":"<int>",				//研究生学历
            "bachelor":"<int>",				//本科学历
            "college":"<int>",				//大专学历
            "tecSecondary":"<int>",			//中专学历
            "tecActivists":"<int>",			//科技活动人员
            "radNumber":"<int>",			//研究与试验发展(R&D)人员
            "returnees":"<int>",			//留学回国人员
            "talents":"<int>",				//千人计划人数
            "trainee":"<int>",				//接纳大学生、研究生实习人员(人/天)
            "employment":"<int>"			//接纳应届毕业生就业人员
        },
		"enterprise":{
            "enterpriseId":"<string>",          //企业/团队的唯一Id
            "teamName":"<string>",				//表示企业/团队的名称
        }
    },
    "code":0,
    "msg":"success"
}
```

请求失败：

```json
{
	"data":null,
    "code":0,
    "msg":"employeeId doesn't exist"
}
```



#### 获取全部企业/团队从业人员的基本情况

**简要描述：**

获取全部企业/团队从业人员的基本情况

**请求URL：**

- `/api/employee`

**请求方式：**

- GET（带token）

**返回值：**

```json
{
	"data":[{
        "employeeId":"<string>",		//企业/团队从业情况表唯一Id
        "enterpriseId":"<string>",		//表示企业/团队的唯一Id
        "teamName":"<string>",			//表示企业/团队的名称
        "employees":"<int>",				//在孵企业/团队从业人员
    },{
        "employeeId":"<string>",		//企业/团队从业情况表唯一Id
        "enterpriseId":"<string>",		//表示企业/团队的唯一Id
        "teamName":"<string>",			//表示企业/团队的名称
        "employees":"<int>",				//在孵企业/团队从业人员
    },
    	......
    ],
    "code":0,
    "msg":"success"
}
```



#### 增加某个企业/团队的从业人员情况

**简要描述：**

增加某个企业/团队从业人员详细情况

**请求URL：**

- `/api/employee`

**请求方式：**

- POST（带token）

**参数：**

```json
{
    "enterpriseId":"<string>",			//表示企业/团队的唯一Id
    "employees":"<int>",					//在孵企业/团队从业人员
    "doctor":"<int>",					//博士
    "master":"<int>",					//硕士
    "graduate":"<int>",					//研究生学历
    "bachelor":"<int>",					//本科学历
    "college":"<int>",					//大专学历
    "tecSecondary":"<int>",				//中专学历
    "tecActivists":"<int>",				//科技活动人员
    "radNumber":"<int>",				//研究与试验发展(R&D)人员
    "returnees":"<int>",				//留学回国人员
    "talents":"<int>",					//千人计划人数
    "trainee":"<int>",					//接纳大学生、研究生实习人员(人/天)
    "employment":"<int>"				//接纳应届毕业生就业人员
}
```

**返回值：**

请求成功：

```json
{
    "data":{
       	"employeeId":"<string>",		//企业/团队从业情况表唯一Id
    },
    "code":0，
    "msg":"success",
}
```

请求失败：

```json
{
    "data":null,
    "code":-1,
    "msg":"enterpriseId doesn't exist"
}
```



#### 修改某个企业/团队从业人员详细情况

**简要描述：**

修改某个企业/团队从业人员详细情况

**请求URL：**

- `/api/employee`

**请求方式：**

- PUT（带token）

**参数：**

```json
{
		"employeeId":"<string>",		//企业/团队从业情况表唯一Id
        "employees":"<int>",				//在孵企业/团队从业人员
        "doctor":"<int>",				//博士
        "master":"<int>",				//硕士
        "graduate":"<int>",				//研究生学历
        "bachelor":"<int>",				//本科学历
        "college":"<int>",				//大专学历
        "tecSecondary":"<int>",			//中专学历
        "tecActivists":"<int>",			//科技活动人员
        "radNumber":"<int>",			//研究与试验发展(R&D)人员
        "returnees":"<int>",			//留学回国人员
        "talents":"<int>",				//千人计划人数
        "trainee":"<int>",				//接纳大学生、研究生实习人员(人/天)
        "employment":"<int>"			//接纳应届毕业生就业人员
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "employeeId":"<string>",		//企业/团队从业情况表唯一Id
    },
    "code":0，
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"employeeId doesn't exist"
}
```



#### 删除某个企业/团队从业人员详细情况

**简要描述：**

删除某个企业/团队从业人员详细情况

**请求URL：**

- `/api/employee`

**请求方式：**

- DELETE（带token）

**参数：**

```json
{
    "employeeId":"<string>",		//企业/团队从业情况表唯一Id
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "employeeId":"<string>",		//企业/团队从业情况表唯一Id
    },
    "code":0，
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"employeeId not exist"
}
```



## **在孵企业知识产权情况**（property）

#### 获取某个在孵企业知识产权详细情况

**简要描述：**

获取某个在孵企业知识产权详细情况

**请求URL：**

- `/api/property/{propertyId}`

**请求方式：**

- GET（带token）

**返回值：**

请求成功：

```json
{
    "data":{
        "property":{
            "propertyId":"<string>",			//在孵企业知识产权情况表唯一Id
            "applications":"<int>",				//当年知识产权申请数
            "applicationsPatent":"<int>",		//当年知识产权申请数中的专利发明数
            "patentNum":"<string>",				//当年知识产权申请数中专利发明对应的专利号
            "softNum":"<string>",				//当年知识产权申请数中专利发明对应的软著登记号
            "trademarkNum":"<string>",			//当年知识产权申请数中专利发明对应的商标号
            "granted":"<int>",					//当年知识产权授权数
            "grantedPatent":"<int>",			//当年知识产权授权数中的发明专利数
            "valid":"<int>",					//拥有有效知识产权数
            "validPatent":"<int>",				//拥有有效知识产权数中的发明专利数
            "softCopyright":"<int>",			//拥有有效知识产权数中的软件著作权数
            "plantVariety":"<int>",				//拥有有效知识产权数中的植物新品种（神奇的字段）数
            "icLayout":"<int>",					//拥有有效知识产权数中的集成电路布图数
            "foreignPatents":"<int>",			//购买国外技术专利
            "contractTransaction":"<int>",		//技术合同交易
            "contractTurnover":"<Long>",		//技术合同交易额
            "projectNum":"<int>",				//当年承担国家级科技计划项目数
            "awards":"<int>",					//当年获得省级以上奖励
        },
        "enterprise":{
            "enterpriseId":"<string>",          //企业/团队的唯一Id
            "teamName":"<string>",				//表示企业/团队的名称
        }
    },
    "code":0,
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1,
	"msg":"propertyId not exist"
}
```



#### 获取全部在孵企业知识产权基本情况

**简要描述：**

获取全部在孵企业知识产权基本情况

**请求URL：**

- `/api/property`

**请求方式：**

- GET（带token）

**返回值：**

```json
{
	"data":[{
        "propertyId":"<string>",			//在孵企业知识产权情况表唯一Id
        "enterpriseId":"<string>",          //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
        "teamName":"<string>",				//表示企业/团队的名称
        "applications":"<int>",				//当年知识产权申请数
        "granted":"<int>",					//当年知识产权授权数
        "valid":"<int>",					//拥有有效知识产权数
    },{
        "propertyId":"<string>",			//在孵企业知识产权情况表唯一Id
        "enterpriseId":"<string>",          //表示企业/团队表的唯一Id，也是企业/团队的唯一Id
        "teamName":"<string>",				//表示企业/团队的名称
        "applications":"<int>",				//当年知识产权申请数
        "granted":"<int>",					//当年知识产权授权数
        "valid":"<int>",					//拥有有效知识产权数
    },
    ......
    ],
    "code":0,
	"msg":"success"
}
```



#### 增加某个在孵企业知识产权情况

**简要描述：**

增加某个在孵企业知识产权情况

**请求URL：**

- `/api/property`

**请求方式：**

- POST（带token）

**参数：**

```json
{
    "enterpriseId":"<string>",			//表示企业/团队的唯一Id
    "applications":"<int>",				//当年知识产权申请数
    "applicationsPatent":"<int>",		//当年知识产权申请数中的专利发明数
    "patentNum":"<string>",				//当年知识产权申请数中专利发明对应的专利号
    "softNum":"<string>",				//当年知识产权申请数中专利发明对应的软著登记号
    "trademarkNum":"<string>",			//当年知识产权申请数中专利发明对应的商标号
    "granted":"<int>",					//当年知识产权授权数
    "grantedPatent":"<int>",			//当年知识产权授权数中的发明专利数
    "valid":"<int>",					//拥有有效知识产权数
    "validPatent":"<int>",				//拥有有效知识产权数中的发明专利数
    "softCopyright":"<int>",			//拥有有效知识产权数中的软件著作权数
    "plantVariety":"<int>",				//拥有有效知识产权数中的植物新品种（神奇的字段）数
    "icLayout":"<int>",					//拥有有效知识产权数中的集成电路布图数
    "foreignPatents":"<int>",			//购买国外技术专利
    "contractTransaction":"<int>",		//技术合同交易
    "contractTurnover":"<Long>",		//技术合同交易额
    "projectNum":"<int>",				//当年承担国家级科技计划项目数
    "awards":"<int>",					//当年获得省级以上奖励
}
```

**返回值：**

请求成功：

```json
{
    "data":{
		"propertyId":"<string>",		//在孵企业知识产权情况表唯一Id
    },
    "code":0，
    "msg":"success",
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"enterpriseId doesn't exist"
}
```



#### 删除某个在孵企业知识产权情况

**简要描述：**

删除某个在孵企业知识产权情况

**请求URL：**

- `/api/property`

**请求方式：**

- DELETE（带token）

**参数：**

```json
{
    "propertyId":"<string>",		//产权情况表唯一Id
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "propertyId":"<string>",			//在孵企业知识产权情况表唯一Id
    },
    "code":0，
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"propertyId not exist"
}
```



#### 修改某个在孵企业知识产权情况

**简要描述：**

修改某个在孵企业知识产权情况

**请求URL：**

- `/api/property`

**请求方式：**

- PUT（带token）

**参数：**

```json
{
	"propertyId":"<string>",			//在孵企业知识产权情况表唯一Id
    "applications":"<int>",				//当年知识产权申请数
    "applicationsPatent":"<int>",		//当年知识产权申请数中的专利发明数
    "patentNum":"<string>",				//当年知识产权申请数中专利发明对应的专利号
    "softNum":"<string>",				//当年知识产权申请数中专利发明对应的软著登记号
    "trademarkNum":"<string>",			//当年知识产权申请数中专利发明对应的商标号
    "granted":"<int>",					//当年知识产权授权数
    "grantedPatent":"<int>",			//当年知识产权授权数中的发明专利数
    "valid":"<int>",					//拥有有效知识产权数
    "validPatent":"<int>",				//拥有有效知识产权数中的发明专利数
    "softCopyright":"<int>",			//拥有有效知识产权数中的软件著作权数
    "plantVariety":"<int>",				//拥有有效知识产权数中的植物新品种（神奇的字段）数
    "icLayout":"<int>",					//拥有有效知识产权数中的集成电路布图数
    "foreignPatents":"<int>",			//购买国外技术专利
    "contractTransaction":"<int>",		//技术合同交易
    "contractTurnover":"<Long>",		//技术合同交易额
    "projectNum":"<int>",				//当年承担国家级科技计划项目数
    "awards":"<int>",					//当年获得省级以上奖励
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "propertyId":"<string>",		//在孵企业知识产权情况表唯一Id
    },
    "code":0，
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"propertyId doesn't exist"
}
```



## **在孵企业科技活动情况**（activity）

#### 获取某个在孵企业科技活动详细情况

**简要描述：**

获取某个在孵企业科技活动详细情况

**请求URL：**

- `/api/activity/{activityId}`

**请求方式：**

- GET（带token）

**返回：**

```json
{
	"activity":{
        "activityId":"<string>",			//活动情况表唯一Id
        "underProjects":"<int>",			//承担各类计划项目
        "nationalProject":"<int>",			//国家级项目
        "expenditure":"<int>",				//科技活动经费支出总额
        "radExpenditure":"<int>",			//研究与试验发展（R&D）经费支出 (千元)
        "productExpenditure":"<int>",		//新产品开发经费支出
        "govermentGrant":"<int>",			//政府拨款
        "selfRaised":"<int>",				//企业自筹
    },
    "enterprise":{
        "enterpriseId":"<string>",          //企业/团队的唯一Id
	    "teamName":"<string>",				//表示企业/团队的名称
    }
}
```



#### 获取全部在孵企业科技活动基本情况

**简要描述：**

获取全部在孵企业科技活动基本情况

**请求URL：**

- `/api/activity`

**请求方式：**

- GET（带token）

**返回值：**

```json
{
	"data":[{
		"activityId":"<string>",			//活动情况表唯一Id
        "enterpriseId":"<string>",          //企业/团队的唯一Id
        "underProjects":"<int>",			//承担各类计划项目
        "nationalProject":"<int>",			//国家级项目
        "expenditure":"<int>",				//科技活动经费支出总额
    },{
		"activityId":"<string>",			//活动情况表唯一Id
        "enterpriseId":"<string>",          //企业/团队的唯一Id
        "underProjects":"<int>",			//承担各类计划项目
        "nationalProject":"<int>",			//国家级项目
        "expenditure":"<int>",				//科技活动经费支出总额
    },
    ......
    ],
    "code":0,
	"msg":"success"
}
```



#### 增加某个在孵企业科技活动基本情况

**简要描述：**

增加某个在孵企业科技活动基本情况

**请求URL：**

- `/api/activity`

**请求方式：**

- POST（带token）

**参数：**

```json
{
	"enterpriseId":"<string>",			//表示企业/团队的唯一Id
	"underProjects":"<int>",			//承担各类计划项目
    "nationalProject":"<int>",			//国家级项目
    "expenditure":"<int>",				//科技活动经费支出总额
    "radExpenditure":"<int>",			//研究与试验发展（R&D）经费支出 (千元)
    "productExpenditure":"<int>",		//新产品开发经费支出
    "govermentGrant":"<int>",			//政府拨款
    "selfRaised":"<int>",				//企业自筹
}
```

**返回值：**

请求成功：

```json
{
    "data":{
		"activityId":"<string>",		//在孵企业科技活动表唯一Id
    },
    "code":0，
    "msg":"success",
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"enterpriseId doesn't exist"
}
```



#### 删除某个在孵企业科技活动基本情况

删除某个在孵企业科技活动基本情况

**请求URL：**

- `/api/activity`

**请求方式：**

- DELETE（带token）

**参数：**

```json
{
    "activityId":"<string>",		//科技活动表唯一Id
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "activityId":"<string>",			//在孵企业科技活动表唯一Id
    },
    "code":0，
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"activityId doesn't exist"
}
```



#### 修改某个在孵企业科技活动基本情况

**简要描述：**

修改某个在孵企业科技活动基本情况

**请求URL：**

- `/api/activitiy`

**请求方式：**

- PUT（带token）

**参数：**

```json
{
	"activityId":"<string>",			//活动情况表唯一Id
    "underProjects":"<int>",			//承担各类计划项目
    "nationalProject":"<int>",			//国家级项目
    "expenditure":"<int>",				//科技活动经费支出总额
    "radExpenditure":"<int>",			//研究与试验发展（R&D）经费支出 (千元)
    "productExpenditure":"<int>",		//新产品开发经费支出
    "govermentGrant":"<int>",			//政府拨款
    "selfRaised":"<int>",				//企业自筹
}
```

**返回值：**

请求成功：

```json
{
    "data":{
        "activityId":"<string>",		//在孵企业科技活动表唯一Id
    },
    "code":0，
    "msg":"success"
}
```

请求失败：

```json
{
    "data":null,
    "code":-1，
    "msg":"propertyId doesn't exist"
}
```

