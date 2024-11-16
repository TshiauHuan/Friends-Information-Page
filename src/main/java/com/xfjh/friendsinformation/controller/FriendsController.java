package com.xfjh.friendsinformation.controller;


import com.xfjh.friendsinformation.pojo.Friends;
import com.xfjh.friendsinformation.pojo.Result;
import com.xfjh.friendsinformation.utils.XmlParserUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FriendsController {
    @RequestMapping("/listFriends")
    public Result list() {

        //1.加载并解析Friends.xml
        String file = this.getClass().getClassLoader().getResource("Friends.xml").getFile();
        System.out.println(file);
        List<Friends> FriendsList = XmlParserUtils.parse(file, Friends.class);
        //2.对数据进行转换处理 -gender,hobby
        FriendsList.stream().forEach(Friends -> {
            //处理gender 1：男 ，2：女
            String gender = Friends.getGender();
            if ("1".equals(gender)) {
                Friends.setGender("男");
            } else if ("2".equals(gender)) {
                Friends.setGender("女");
            }

            //处理hobby 1: 打游戏, 2: 踢足球 , 3: 唱歌
            String hobby = Friends.getHobby();
            if ("1".equals(hobby)) {
                Friends.setHobby("打游戏");
            } else if ("2".equals(hobby)) {
                Friends.setHobby("踢足球");
            } else if ("3".equals(hobby)) {
                Friends.setHobby("唱歌");
            }
        });

        //3.响应数据
        return Result.success(FriendsList);


    }

}
