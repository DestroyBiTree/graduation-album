package com.nwu.graduationalbum.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: NwuGraduationAlbum
 * @description: 封装总返回数据
 * @author: TD.Miracle
 * @create: 2022-05-20 14:32
 **/
@Data
@TableName("result_2022_all")
public class ResultVo implements Serializable {

    @TableId("XH")
    private String number;                  // 学号

    /*基本数据*/
    @TableField("name")
    private String name;                    // 姓名
    @TableField("gender")
    private String gender;                    // 性别
    @TableField("enrollment_data")
    private String enrollmentYear;          // 入学日期
    @TableField("educational_system")
    private int educationalSystem;          // 在校时间， 两年，三年，四年等
    @TableField("stu_type")
    private String studentType;          // 学生类别：本科生，研究生
    @TableField("college")
    private String college;          // 学院
    @TableField("major")
    private String major;          // 专业

    /*宿舍数据*/
    private String campus;      // 校区名称，长安校区，太白校区等
    private String apartment;   // 公寓楼号， 2号楼，等等
    private int dormitory;      // 宿舍号
    private String bed;         // 床位号
    private int roommates;      // 宿舍人数

    /*课程数据*/
    @TableField("first_course_data")
    private String firstCourseTime;     // 第一门课的上课日期
    private String firstCourseName;     // 第一门课的课程名称
    private int totalCourse;           // 总上课门数
    @TableField("total_credict")
    private int totalCredit;            // 总学分数
    private int totalClassHour;        // 上课总时长

    /*食堂数据*/
    private String firstCanteen;        // 第一次进食堂时间
    private String firstMoney;          // 第一次消费金额
    private String firstPlace;           // 第一次去的窗口
    private String favoritePlace;        // 最喜欢的窗口
    private int favoriteTime;            // 最喜欢的菜吃的次数
    private int allTime;                 // 总共去食堂次数
    private String eatRank;              // 吃饭排行

    /*pos机数据*/
    private String electricMoney;        //买电总金额

    /*图书馆数据*/
    private String firstLoanTime;       // 第一次去图书馆借书的时间
    private String firstBook;           // 第一次借的书
    private String longestBook;         // 借阅时间最长的书
    private int longestTime;            // 借阅最长时间
    private String loanSum;             // 总共借阅次数
    private String loanRank;            // 借阅排名
    private String firstCameLibrary;    // 第一次去图书馆的时间
    private String sumLibrary;          // 一共去图书馆次数

    /*体育馆数据*/
    private String firstGymTime;       // 第一次去体育馆时间
    private String firstSport;         // 第一次的运动
    private int sumSport;              // 去体育馆的总数
    private int basketball;            // 篮球总数
    @TableField("pin")
    private int pingPang;              // 乒乓球总数
    private int badminton;             // 羽毛球总数
    private String maxSport;           // 次数最多的运动
    private String sportRank;          // 运动总次数排名


}
