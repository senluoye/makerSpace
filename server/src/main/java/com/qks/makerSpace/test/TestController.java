package com.qks.makerSpace.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个类仅作为测试类使用
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * {
     *     "error": 0,
     *     "status": "success",
     *     "results": [
     *         {
     *             "currentCity": "青岛",
     *             "index": [
     *                 {
     *                     "title": "穿衣",
     *                     "zs": "较冷",
     *                     "tipt": "穿衣指数",
     *                     "des": "建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"
     *                 },
     *                 {
     *                     "title": "紫外线强度",
     *                     "zs": "中等",
     *                     "tipt": "紫外线强度指数",
     *                     "des": "属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"
     *                 }
     *             ]
     *
     *         }
     *     ]
     * }
     * @param jsonObject
     */

    @RequestMapping(value = "", method = RequestMethod.POST)
    private void getOldForm(@RequestBody JSONObject jsonObject) {

        int error = jsonObject.getInteger("error");
        System.out.println("error:" + error);

        //提取出status为 success
        String status = jsonObject.getString("status");
        System.out.println("status:" + status);

        //注意：results中的内容带有中括号[]，所以要转化为JSONArray类型的对象
        JSONArray result = jsonObject.getJSONArray("results");

        for (int i = 0; i < result.size(); i++) {
            //提取出currentCity为 青岛
            String currentCity = result.getJSONObject(i).getString("currentCity");
            System.out.println("currentCity:" + currentCity);

            //注意：index中的内容带有中括号[]，所以要转化为JSONArray类型的对象
            JSONArray index = result.getJSONObject(i).getJSONArray("index");

            for (int j = 0; j < index.size(); j++) {
                String title = index.getJSONObject(j).getString("title");
                System.out.println("title:" + title);
                String zs = index.getJSONObject(j).getString("zs");
                System.out.println("zs:" + zs);
                String tipt = index.getJSONObject(j).getString("tipt");
                System.out.println("tipt:" + tipt);
                String des = index.getJSONObject(j).getString("des");
                System.out.println("des:" + des);
            }
        }

    }

    @RequestMapping(value = "aaa", method = RequestMethod.GET)
    private String test() {

        return "宁已经连上了";

    }
}
