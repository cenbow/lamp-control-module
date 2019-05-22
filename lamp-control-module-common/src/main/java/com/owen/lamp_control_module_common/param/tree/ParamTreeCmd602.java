package com.owen.lamp_control_module_common.param.tree;

import com.owen.lamp_control_module_common.bean.RespCode;
import com.owen.lamp_control_module_common.exception.YcException;
import com.owen.lamp_control_module_common.param.base.BaseParam;
import com.owen.lamp_control_module_common.param.component.RGBW;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHex;
import static com.owen.lamp_control_module_common.utils.BinaryConversionUtil.decimalToHexFF;

/**
 * <p>Title: ParamTreeCmd602</p>
 * <p>Description: 实时控制灯具模式
 * 功能版本：v1.1.2
 * 功能时间：2019.1.5
 * ###################################################################################################################
 * 修订记录 (v1.1.2)：
 * 1. 增加了单个随机流水模式3个
 * 2. 增加了整体流水模式1个
 * 3. 增加了整体拖流水模式1个
 * ###################################################################################################################
 * 命令数据: 0x0602
 * -------------------------
 * 1. 整体静态轮播
 * -------------------------
 * 数据：
 * 轮播模式 (1字节)
 * 轮播个数 (1字节)
 * 颜色数据 (4字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：整体静态轮播模式 (RGBW)
 * 轮播个数范围：1-16
 * 轮播时间间隔：200-30000ms
 * -------------------------
 * 示例 1-1：【整体静态轮播，1个颜色，红色，白光0】
 * 01 (静态)
 * 01 (1个轮播)
 * FF 00 00 (红色)
 * 00 (白光)
 * 0000 (轮播时间)
 * -------------------------
 * 示例 1-2：【整体静态轮播，2 个颜色，红、绿，8 秒一次轮播】
 * 01 (静态)
 * 02 (2 个轮播)
 * ff 00 00 00 (通道数据)
 * 00 ff 00 00 (通道数据)
 * 00 08(轮播时间 8s)
 * ###################################################################################################################
 * -------------------------
 * 2. 整体呼吸渐变 W 固定
 * -------------------------
 * 数据：
 * 轮播模式 (1字节)
 * 白光值 (1字节)
 * 渐变粒度 (1字节)
 * 起始停留时间 (1字节)
 * 结束停留时间 (1字节)
 * 轮播个数 (1字节)
 * 颜色数据 (1字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：整体呼吸渐变，W 固定
 * 颜色数据：0-7
 * 渐变粒度：1-10 级
 * 起始、结束停留时间:0-60s
 * 轮播时间间隔：1500-30000ms
 * -------------------------
 * 示例 2:【呼吸渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
 * 02 (呼吸渐变，W 固定)
 * 7D (W 值 125)
 * 05 (渐变粒度 5 级)
 * 00 (暗部保持 0)
 * 02 (亮部保持 2s)
 * 04 (4个颜色轮播)
 * 01 (红)
 * 02 (绿)
 * 03 (蓝)
 * 07 (青)
 * 05 DC(15秒一次轮播)
 * ###################################################################################################################
 * -------------------------
 * 3. 整体呼吸渐变 W 跟随变化，保持整体亮度不变
 * -------------------------
 * 数据：
 * 轮播模式 (1字节)
 * 白光最小值 (1字节)
 * 白光最大值 (1字节)
 * 渐变粒度 (1字节)
 * 起始停留时间 (1字节)
 * 结束停留时间 (1字节)
 * 轮播个数 (1字节)
 * 颜色数据 (1字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：整体呼吸渐变，W 跟随变化，保持整体亮度不变
 * 颜色数据：0-7
 * 渐变粒度：1-10 级
 * 起始、结束停留时间:0-60s
 * 轮播时间间隔：1500-30000ms
 * -------------------------
 * 示例 3:【呼吸渐变，W 跟随变化，保持整体亮度不变】
 * 03 (呼吸渐变，W 保持跟随变化)
 * 1E (w 最小 30)
 * FF (w 最大 255)
 * 05 (渐变粒度 5级)
 * 00 (暗部保持 0)
 * 02 (亮部保持 2s)
 * 04 (4 个颜色轮播)
 * 01 (红)
 * 02 (绿)
 * 03 (蓝)
 * 07 (青)
 * 05 DC (15 秒一次轮播)
 * ###################################################################################################################
 * -------------------------
 * 4. 整体无痕渐变 W 固定
 * -------------------------
 * 数据：
 * 轮播模式 (1字节)
 * 白光值 (1字节)
 * 渐变粒度 (1字节)
 * 起始停留时间 (1字节)
 * 结束停留时间 (1字节)
 * 轮播个数 (1字节)
 * 颜色数据 (1字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：整体无痕渐变，W 固定
 * 颜色数据：0-7
 * 渐变粒度：1-10 级
 * 起始、结束停留时间:0-60s
 * 轮播时间间隔：1500-3000ms
 * -------------------------
 * 示例：【无痕渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
 * 04 (无痕渐变，W 固定)
 * 7D (W 值 125)
 * 05 (渐变粒度 5 级)
 * 00 (暗部保持 0)
 * 02 (亮部保持 2s)
 * 04 (4个颜色轮播)
 * 01 (红)
 * 02 (绿)
 * 03 (蓝)
 * 07 (青)
 * 05 DC (15秒一次轮播)
 * ###################################################################################################################
 * -------------------------
 * 5. 单个随机流水模式，点数随机，颜色固定
 * -------------------------
 * 数据：
 * 节点随机流水模式 (1字节)
 * 点数随机值 (2字节)
 * 流水模式 (1字节)
 * 颜色数据 (4字节)
 * 流水速率 (2字节)
 * 回流时间 (2字节)
 * 轮播间隔时间 (2字节)
 * 主机刷新时间 (2字节)
 * -------------------------
 * 备注：单个随机流水模式，点数随机，颜色固定
 * 颜色数据：RGBW(4字节)
 * 流水模式： 0 普通流水、1 拖尾流水
 * 点数随机值范围：1-512
 * 流水速率范围：1-1000ms
 * 回流时间范围：1-1000ms
 * 轮播时间间隔：100-60000ms
 * 主机刷新时间范围：0-60000ms
 * -------------------------
 * 示例：【随机流水模式，点数随机，颜色固定；点数随机值 36 (表示在 36 内产生随机数)，颜色数据：R=255，G=0，B=0，W=0，普通流水，
 * 流水速率 10ms,回流时间100ms,轮播时间间隔：300ms 一次轮播,主机刷新时间 0】
 * 05 (随机流水模式，点数随机，颜色固定)
 * 00 24 (点数随机值 36，表示在 36以内产生随机数)
 * 00(普通流水)
 * ff (红)
 * 00 (绿)
 * 00 (蓝)
 * 00 (白光)
 * 00 0A (流水速率 10ms)
 * 00 64 (回流时间 100ms)
 * 01 2C (300ms 一次轮播)
 * 00 00 (主机刷新时间)
 * ###################################################################################################################
 * -------------------------
 * 6. 单个随机流水模式，W 固定，颜色随机，点数固定
 * -------------------------
 * 数据：
 * 节点随机流水模式 (1字节)
 * 白光值 (1字节)
 * 点数值 (2字节)
 * 流水模式 (1字节)
 * 颜色随机数据 (1字节)
 * 流水速率 (2字节)
 * 回流时间 (2字节)
 * 轮播间隔时间 (2字节)
 * 主机刷新时间 (2字节)
 * -------------------------
 * 备注：单个随机流水模式，W 固定，颜色随机，点数固定
 * 颜色数据范围：0-7
 * 流水模式： 0 普通流水、1 拖尾流水
 * 点数值范围：1-512
 * 流水速率范围：1-1000ms
 * 回流时间范围：1-1000ms
 * 轮播时间间隔：100-60000ms
 * 主机刷新时间范围：0-60000ms
 * -------------------------
 * 示例 6:【随机流水模式，颜色随机，点数固定；白光值 00，点数值 36，普通流水，颜色随机数据 7 (表示 7 以内颜色随机)，
 * 流水速率 10ms, 回流时间 100ms,轮播时间间隔：300ms 一次轮播，主机刷新时间 0】
 * 06 (随机流水模式，颜色随机，点数固定)
 * 00 (白光值 0)
 * 00 24 (点数固定值 36)
 * 00 (普通流水)
 * 07 (颜色随机值)
 * 00 0A (流水速率 10ms)
 * 00 64 (回流时间 100ms)
 * 01 2C (300ms 一次轮播)
 * 00 00 (主机刷新时间)
 * ###################################################################################################################
 * -------------------------
 * 7. 单个随机流水模式，W 固定，颜色随机，点数随机
 * -------------------------
 * 数据：
 * 节点随机流水模式 (1字节)
 * 白光值 (1字节)
 * 点数随机值 (2字节)
 * 流水模式 (1字节)
 * 颜色随机数据 (1字节)
 * 流水速率 (2字节)
 * 回流时间 (2字节)
 * 轮播间隔时间 (2字节)
 * 主机刷新时间 (2字节)
 * -------------------------
 * 备注：
 * 单个随机流水模式，W 固定，颜色随机，点数随机
 * 颜色随机数据范围：0-7 流水模式： 0 普通流水、1 拖尾流水
 * 点数值随机范围：1-512
 * 流水速率范围：1-1000ms
 * 回流时间范围：1-1000ms
 * 轮播时间间隔：100-60000ms
 * 主机刷新时间范围：0-60000ms
 * -------------------------
 * 示例 7:【随机流水模式，颜色随机，点数随机；白光值 00，点数随机值 36 (表示在 36内产生随机数)，普通流水，
 * 颜色随机值 7 (表示 7 以内颜色随机)，流水速率10ms,回流时间 100ms,轮播时间间隔：300ms 一次轮播，主机刷新时间 0】
 * 07 (随机流水模式，颜色随机，点数随机)
 * 00 (白光值 0)
 * 00 24 (点数随机值36，表示在 36 内产生随机值)
 * 00 (普通流水)
 * 07 (颜色随机值，表示在 7 内产生随机颜色)
 * 00 0A (流水速率 10ms)
 * 00 64 (回流时间 100ms)
 * 01 2C (300ms 一次轮播)
 * 00 00 (主机刷新时间)
 * -------------------------
 * ###################################################################################################################
 * -------------------------
 * 8. 节点流水模式
 * -------------------------
 * 数据：
 * 节点流水模式 (1字节)
 * 起始节点 (2字节)
 * 结束节点 (2字节)
 * 合并点数 (1字节)
 * 颜色数据 (4字节)
 * 底色数据 (4字节)
 * 流水速率 (2字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：
 * 节点流水模式
 * 颜色数据：RGBW(4字节)
 * 合并点数范围：1-50
 * 流水速率：50-1000ms
 * 轮播时间间隔：100-60000ms
 * -------------------------
 * 示例：【节点流水模式,起始节点 1，结束节点 255，合并点数 3，颜色数据红色，底色黑色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
 * 08 (节点流水模式)
 * 00 01 (起始节点 1)
 * 00 FF (结束节点 255)
 * 03 (合并点数)
 * FF (红)
 * 00 (绿)
 * 00 (蓝)
 * 00 (白色)
 * 00 00 00 00 (底色黑色)
 * 00 64 (流水 11 速率 100ms)
 * 01 2C (300ms 一次轮播)
 * -------------------------
 * ###################################################################################################################
 * -------------------------
 * 9. 节点拖尾流水模式
 * -------------------------
 * 数据：
 * 节点拖尾流水模式 (1字节)
 * 起始节点 (2字节)
 * 结束节点 (2字节)
 * 合并点数 (1字节)
 * 颜色数据 (4字节)
 * 底色数据 (4字节)
 * 流水速率 (2字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：
 * 节点拖尾流水模式
 * 节点数范围：1-512
 * 颜色数据：RGBW (4字节)
 * 合并点数范围：1-50
 * 流水速率：50-1000ms
 * 轮播时间间隔：100-60000ms
 * -------------------------
 * 示例：【节点拖尾流水模式，起始节点 1，结束节点 255，合并点数 3，底色黑色，颜色数据红色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
 * 09 (节点流水模式)
 * 00 01 (起始节点 1)
 * 00 FF (结束节点 255)
 * 03 (合并点数)
 * FF (红)
 * 00 (绿)
 * 00 (蓝)
 * 00 (白色)
 * 00 00 00 00 (底色黑色)
 * 00 64  (流水速率 100ms)
 * 01 2C (300ms 一次轮播)
 * -------------------------
 * ###################################################################################################################
 * -------------------------
 * 10. 群发流水模式
 * -------------------------
 * 数据：
 * 群发流水模式 (1字节)
 * 流水模式 (1字节)
 * 起始点数 (2字节)
 * 结束点数 (2字节)
 * 合并点数 (1字节)
 * 颜色数据 (4字节)
 * 底色数据 (4字节)
 * 流水速率 (2字节)
 * 回流时间 (2字节)
 * 轮播间隔时间 (2字节)
 * -------------------------
 * 备注：群发流水模式
 * 流水模式： 0 普通流水、1 拖尾流水
 * 点数范围：1-512
 * 颜色数据：RGBW(4字节)
 * 合并点数范围：1-50
 * 流水速率：50-1000ms
 * 轮播时间间隔：100-60000ms
 * 回流时间范围：0-6000ms
 * -------------------------
 * 示例：【群发流水模式，普通流水，起始点数 1，结束点数 18，合并点数 3，底色黑色，颜色数据红色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
 * 0A (群发流水模式)
 * 00 (普通流水)
 * 00 01 (起始点数)
 * 00 E0 (结束点数 18)
 * 03 (合并点数)
 * FF (红)
 * 00 (绿)
 * 00 (蓝)
 * 00 (白色)
 * 00 00 00 00 (底色黑色)
 * 00 64  (流水速率 100ms)
 * 00 00  (回流时间 0)
 * 01 2C (300ms 一次轮播)
 * -------------------------
 * ###################################################################################################################
 * -------------------------
 * 11. 节点互动模式
 * -------------------------
 * 数据：
 * 互动模式 (1字节)
 * 互动效果 (1字节)
 * 变色模式 (1字节)
 * 流水模式 (1字节)
 * 保持时间 (2字节)
 * 响应机制 (1字节)
 * 颜色数据 (4字节)
 * -------------------------
 * 备注：
 * - 互动模式
 * - 互动效果： 0 变色、1 流水
 * - 变色模式：0 直变、1 渐变
 * - 流水模式：0 普通、1 拖尾
 * - 保持时间：0-600s
 * - 响应机制：0 单个响应，1 联动响应
 * - 颜色数据：RGBW (4字节)
 * -------------------------
 * 示例：【节点互动模式，互动效果变色，变色模式直变，流水模式普通，保持时间 2s, 响应机制单个响应，颜色数据红色】
 * 1E (节点互动模式)
 * 00 (互动效果)
 * 00 (变色模式)
 * 00 (流水模式)
 * 00 02 (保持时间)
 * 00 (响应机制)
 * FF (红)
 * 00 (绿)
 * 00 (蓝)
 * 00 (白色)
 * -------------------------
 * ###################################################################################################################
 * </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/5/7 16:31
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("实时控制灯具模式")
@EqualsAndHashCode(callSuper = true)
public class ParamTreeCmd602 extends BaseParam {

    public static final String CMD = getCmd(ParamTreeCmd602.class.getSimpleName());

    @Override
    protected void setCmd() {
        super.cmd = CMD;
    }

    /**
     * 1 整体静态轮播
     * 2 整体呼吸渐变 W 固定
     * 3 整体呼吸渐变 W 跟随变化，保持整体亮度不变
     * 4 整体无痕渐变 W 固定
     * 5 单个随机流水模式，点数随机，颜色固定
     * 6 单个随机流水模式，W 固定，颜色随机，点数固定
     * 7 单个随机流水模式，W 固定，颜色随机，点数随机
     * 8 节点流水模式
     * 9 节点拖尾流水模式
     * 10 群发流水模式
     * 11 节点互动模式
     */
    public static final int OVERALL_STATIC_MODE = 1;
    public static final int OVERALL_BREATHING_GRADIENT_W_FIXED_MODE = 2;
    public static final int OVERALL_BREATHING_GRADIENT_W_FOLLOW_MODE = 3;
    public static final int OVERALL_SEAMLESS_GRADIENT_W_FIXED_MODE = 4;
    public static final int SINGLE_RANDOM_FLOW_MODE_POINT_RANDOM_COLOR_FIXED_MODE = 5;
    public static final int SINGLE_RANDOM_FLOW_MODE_W_FIXED_COLOR_RANDOM_POINT_FIXED_MODE = 6;
    public static final int SINGLE_RANDOM_FLOW_MODE_W_FIXED_COLOR_RANDOM_POINT_RANDOM_MODE = 7;
    public static final int NODE_FLOW_MODE = 8;
    public static final int NODE_TAILING_FLOW_MODE = 9;
    public static final int MASS_FLOW_PATTERN_MODE = 10;
    public static final int NODE_INTERACTION_MODE = 30;


    /**
     * 颜色数据对照表
     * 0 黑色
     * 1 白色
     * 2 红色
     * 3 绿色
     * 4 蓝色
     * 5 黄色
     * 6 品红
     * 7 青色
     */
    public static final int COLOR_BLACK = 0;
    public static final int COLOR_WRITE = 1;
    public static final int COLOR_RED = 2;
    public static final int COLOR_GREEN = 3;
    public static final int COLOR_BLUE = 4;
    public static final int COLOR_YELLOW = 5;
    public static final int COLOR_MAGENTA = 6;
    public static final int COLOR_CYAN = 7;

    /**
     * 轮播模式
     */
    @ApiModelProperty("轮播模式")
    private int carouselMode;

    /**
     * 轮播次数
     */
    @ApiModelProperty("轮播次数")
    private int carouselNum;

    /**
     * 颜色数组
     */
    @ApiModelProperty("RGBW数组")
    private List<RGBW> rgbws = new ArrayList<>();

    /**
     * 白光值 (1字节)
     */
    @ApiModelProperty("白光值 (1字节)")
    private int whiteLightValue;

    /**
     * 白光最大值 (1字节)
     */
    @ApiModelProperty("白光最大值 (1字节)")
    private int whiteLightValueMax;

    /**
     * 白光最小值 (1字节)
     */
    @ApiModelProperty("白光最小值 (1字节)")
    private int whiteLightValueMin;

    /**
     * 渐变粒度 (1字节)
     */
    @ApiModelProperty("渐变粒度 (1字节)")
    private int gradientGranularity;

    /**
     * 起始停留时间 (1字节)
     */
    @ApiModelProperty("起始停留时间 (1字节)")
    private int startingTime;

    /**
     * 结束停留时间 (1字节)
     */
    @ApiModelProperty("结束停留时间 (1字节)")
    private int endTime;

    /**
     * 轮播颜色列表 (N字节)
     */
    @ApiModelProperty("轮播颜色列表 (N字节)")
    private int[] carouselColorList;

    /**
     * 轮播间隔时间 (2字节)
     */
    @ApiModelProperty("轮播间隔时间 (2字节)")
    private int carouselInterval;

    /**
     * 节点随机流水模式 (1字节)
     */
    @ApiModelProperty("节点随机流水模式 (1字节)")
    private int nodeRandomFlowMode;

    /**
     * 点数值 (2字节)
     */
    @ApiModelProperty("点数值 (2字节)")
    private int pointValue;

    /**
     * 点数随机值 (2字节)
     */
    @ApiModelProperty("点数随机值 (2字节)")
    private int pointRandomValue;

    /**
     * 流水模式 (1字节)
     */
    @ApiModelProperty("流水模式 (1字节)")
    private int flowMode;

    /**
     * 颜色数据 (4字节)
     */
    @ApiModelProperty("颜色数据 (4字节)")
    private RGBW colorData;

    /**
     * 颜色随机数据 (1字节)
     */
    @ApiModelProperty("颜色随机数据 (1字节)")
    private int colorRandomData;

    /**
     * 流水速率 (2字节)
     */
    @ApiModelProperty("流水速率 (2字节)")
    private int flowRate;

    /**
     * 回流时间 (2字节)
     */
    @ApiModelProperty("回流时间 (2字节)")
    private int refluxTime;

    /**
     * 主机刷新时间 (2字节)
     */
    @ApiModelProperty("主机刷新时间 (2字节)")
    private int hostRefreshTime;

    /**
     * 起始节点 (2字节)
     */
    @ApiModelProperty("起始节点 (2字节)")
    private int startingNode;

    /**
     * 结束节点 (2字节)
     */
    @ApiModelProperty("结束节点 (2字节)")
    private int endNode;

    /**
     * 合并点数 (1字节)
     */
    @ApiModelProperty("合并点数 (1字节)")
    private int mergingPoints;

    /**
     * 底色数据 (4字节)
     */
    @ApiModelProperty("底色数据 (4字节)")
    private RGBW backgroundData;

    /**
     * 互动效果 (1字节)
     */
    @ApiModelProperty("互动效果 (1字节)")
    private int interactiveEffect;

    /**
     * 变色模式 (1字节)
     */
    @ApiModelProperty("变色模式 (1字节)")
    private int colorChangeMode;

    /**
     * 保持时间 (2字节)
     */
    @ApiModelProperty("保持时间 (2字节)")
    private int holdTime;

    /**
     * 响应机制 (1字节)
     */
    @ApiModelProperty("响应机制 (1字节)")
    private int responseMechanism;

    /**
     * 1 整体静态轮播
     * 2 整体呼吸渐变 W 固定
     * 3 整体呼吸渐变 W 跟随变化，保持整体亮度不变
     * 4 整体无痕渐变 W 固定
     * 5 单个随机流水模式，点数随机，颜色固定
     * 6 单个随机流水模式，W 固定，颜色随机，点数固定
     * 7 单个随机流水模式，W 固定，颜色随机，点数随机
     * 8 节点流水模式
     * 9 节点拖尾流水模式
     * 10 群发流水模式
     * 11 节点互动模式
     *
     * @return 结果
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(decimalToHex(carouselMode));
        switch (carouselMode) {
            // 1 整体静态轮播
            case OVERALL_STATIC_MODE:
                return staticMode(message);
            // 2 整体呼吸渐变 W 固定
            case OVERALL_BREATHING_GRADIENT_W_FIXED_MODE:
                return breathingGradientWFixedMode(message);
            // 3 整体呼吸渐变 W 跟随变化，保持整体亮度不变
            case OVERALL_BREATHING_GRADIENT_W_FOLLOW_MODE:
                return breathingGradientWFollowMode(message);
            // 4 整体无痕渐变 W 固定
            case OVERALL_SEAMLESS_GRADIENT_W_FIXED_MODE:
                return seamlessGradientWFixedMode(message);
            // 5 单个随机流水模式，点数随机，颜色固定
            case SINGLE_RANDOM_FLOW_MODE_POINT_RANDOM_COLOR_FIXED_MODE:
                return singleRandomFlowModePointRandomColorFixedMode(message);
            // 6 单个随机流水模式，W 固定，颜色随机，点数固定
            case SINGLE_RANDOM_FLOW_MODE_W_FIXED_COLOR_RANDOM_POINT_FIXED_MODE:
                return singleRandomFlowModeWFixedColorRandomPointFixedMode(message);
            // 7 单个随机流水模式，W 固定，颜色随机，点数随机
            case SINGLE_RANDOM_FLOW_MODE_W_FIXED_COLOR_RANDOM_POINT_RANDOM_MODE:
                return singleRandomFlowModeWFixedColorRandomPointRandomMode(message);
            // 8 节点流水模式
            case NODE_FLOW_MODE:
                return nodeFlowMode(message);
            // 9 节点拖尾流水模式
            case NODE_TAILING_FLOW_MODE:
                return nodeTailingFlowMode(message);
            // 10 群发流水模式
            case MASS_FLOW_PATTERN_MODE:
                return massFlowPatternMode(message);
            // 11 节点互动模式
            case NODE_INTERACTION_MODE:
                return nodeInteractionMode(message);
            default:
                throw new YcException(RespCode.FAIL);
        }
    }

    /**
     * 快速构建测试对象
     * 【静态红色，不带白光】
     * D5 C6 D5 C6(起始符)06 02(命令)00 05(数据长度 5)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 01(静态)
     * ff 00 00(红色)
     * 00(不带白光)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     * -------------------
     * -------------------
     * 【呼吸渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒 度 5 级】
     * D5 C6 D5 C6(起始符)06 02(命令)00 0C(数据长度 12)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 02(呼吸渐变，W 固定)
     * 7D(W 值 125)
     * 05(渐变粒度 5 级)
     * 00(起始停留时间 0s)
     * 02(结束停留时间 2s)
     * 04(4个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     * -------------------
     * -------------------
     * 【呼吸渐变，W 跟随变化，保持整体亮度不变】
     * D5 C6 D5 C6(起始符)06 02(命令)00 0C(数据长度 12)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 03 (呼吸渐变，W 保持跟随变化)
     * 1E(w 最小 30)
     * FF(w 最大 255)
     * 05(渐变粒 度 5 级)
     * 00(起始停留时间 0s)
     * 02(结束停留时间 2s)
     * 04(4 个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     * -------------------
     * -------------------
     * 【无痕渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒 度 5 级】
     * D5 C6 D5 C6(起始符)
     * -------------------
     * 06 02(命令)
     * 00 0C(数据长度 12)
     * 12 04 12 0E 09 10 03(时间)
     * 04(无痕 渐变，W 固定)
     * 7D(W 值 125)
     * 05(渐变粒度 5 级)
     * 00(起始保持时间 0)
     * 02(结束保持时间 2s)
     * 04(4 个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     *
     * @param carouselMode 轮播模式
     * @return 结果
     */
    public static ParamTreeCmd602 build(int carouselMode) {
        try {
            if (checkMode(carouselMode)) {
                switch (carouselMode) {
                    // 1 整体静态轮播
                    case OVERALL_STATIC_MODE:
                        return build01Mock(carouselMode);
                    // 2 整体呼吸渐变 W 固定
                    case OVERALL_BREATHING_GRADIENT_W_FIXED_MODE:
                        return build02Mock(carouselMode);
                    // 3 整体呼吸渐变 W 跟随变化，保持整体亮度不变
                    case OVERALL_BREATHING_GRADIENT_W_FOLLOW_MODE:
                        return build03Mock(carouselMode);
                    // 4 整体无痕渐变 W 固定
                    case OVERALL_SEAMLESS_GRADIENT_W_FIXED_MODE:
                        return build04Mock(carouselMode);
                    // 5 单个随机流水模式，点数随机，颜色固定
                    case SINGLE_RANDOM_FLOW_MODE_POINT_RANDOM_COLOR_FIXED_MODE:
                        return build05Mock(carouselMode);
                    // 6 单个随机流水模式，W 固定，颜色随机，点数固定
                    case SINGLE_RANDOM_FLOW_MODE_W_FIXED_COLOR_RANDOM_POINT_FIXED_MODE:
                        return build06Mock(carouselMode);
                    // 7 单个随机流水模式，W 固定，颜色随机，点数随机
                    case SINGLE_RANDOM_FLOW_MODE_W_FIXED_COLOR_RANDOM_POINT_RANDOM_MODE:
                        return build07Mock(carouselMode);
                    // 8 节点流水模式
                    case NODE_FLOW_MODE:
                        return build08Mock(carouselMode);
                    // 9 节点拖尾流水模式
                    case NODE_TAILING_FLOW_MODE:
                        return build09Mock(carouselMode);
                    // 10 群发流水模式
                    case MASS_FLOW_PATTERN_MODE:
                        return build10Mock(carouselMode);
                    // 11 节点互动模式
                    case NODE_INTERACTION_MODE:
                        return build11Mock(carouselMode);
                    default:
                        throw new YcException(RespCode.FAIL);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 示例：【节点互动模式，互动效果变色，变色模式直变，流水模式普通，保持时间 2s, 响应机制单个响应，颜色数据红色】
     * 1E (节点互动模式)
     * 00 (互动效果)
     * 00 (变色模式)
     * 00 (流水模式)
     * 00 02 (保持时间)
     * 00 (响应机制)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     */
    private static ParamTreeCmd602 build11Mock(int carouselMode) {
        return build11(carouselMode,
                0,
                0,
                0,
                2,
                0,
                new RGBW(255, 0, 0, 0));
    }

    /**
     * -------------------------
     * 示例：【群发流水模式，普通流水，起始点数 1，结束点数 18，合并点数 3，底色黑色，颜色数据红色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
     * 0A (群发流水模式)
     * 00 (普通流水)
     * 00 01 (起始点数)
     * 00 E0 (结束点数 18)
     * 03 (合并点数)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     * 00 00 00 00 (底色黑色)
     * 00 64  (流水速率 100ms)
     * 00 00  (回流时间 0)
     * 01 2C (300ms 一次轮播)
     * -------------------------
     */
    private static ParamTreeCmd602 build10Mock(int carouselMode) {
        return build10(carouselMode,
                0,
                1,
                18,
                3,
                new RGBW(255, 0, 0, 0),
                new RGBW(0, 0, 0, 0),
                100,
                0,
                300);
    }

    /**
     * 示例：【节点拖尾流水模式，起始节点 1，结束节点 255，合并点数 3，底色黑色，颜色数据红色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
     * 09 (节点流水模式)
     * 00 01 (起始节点 1)
     * 00 FF (结束节点 255)
     * 03 (合并点数)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     * 00 00 00 00 (底色黑色)
     * 00 64  (流水速率 100ms)
     * 01 2C (300ms 一次轮播)
     */
    private static ParamTreeCmd602 build09Mock(int carouselMode) {
        return build09(carouselMode,
                1,
                255,
                3,
                new RGBW(255, 0, 0, 0),
                new RGBW(0, 0, 0, 0),
                100,
                300);
    }

    /**
     * 示例：【节点流水模式,起始节点 1，结束节点 255，合并点数 3，颜色数据红色，底色黑色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
     * 08 (节点流水模式)
     * 00 01 (起始节点 1)
     * 00 FF (结束节点 255)
     * 03 (合并点数)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     * 00 00 00 00 (底色黑色)
     * 00 64 (流水 11 速率 100ms)
     * 01 2C (300ms 一次轮播)
     */
    private static ParamTreeCmd602 build08Mock(int carouselMode) {
        return build08(carouselMode,
                1,
                255,
                3,
                new RGBW(255, 0, 0, 0),
                new RGBW(0, 0, 0, 0),
                100,
                300);
    }

    /**
     * 示例 7:【随机流水模式，颜色随机，点数随机；白光值 00，点数随机值 36 (表示在 36内产生随机数)，普通流水，
     * 颜色随机值 7 (表示 7 以内颜色随机)，流水速率10ms,回流时间 100ms,轮播时间间隔：300ms 一次轮播，主机刷新时间 0】
     * 07 (随机流水模式，颜色随机，点数随机)
     * 00 (白光值 0)
     * 00 24 (点数随机值36，表示在 36 内产生随机值)
     * 00 (普通流水)
     * 07 (颜色随机值，表示在 7 内产生随机颜色)
     * 00 0A (流水速率 10ms)
     * 00 64 (回流时间 100ms)
     * 01 2C (300ms 一次轮播)
     * 00 00 (主机刷新时间)
     */
    private static ParamTreeCmd602 build07Mock(int carouselMode) {
        return build07(carouselMode,
                0,
                36,
                0,
                7,
                10,
                100,
                300,
                0);
    }

    /**
     * 示例 6:【随机流水模式，颜色随机，点数固定；白光值 00，点数值 36，普通流水，颜色随机数据 7 (表示 7 以内颜色随机)，
     * 流水速率 10ms, 回流时间 100ms,轮播时间间隔：300ms 一次轮播，主机刷新时间 0】
     * 06 (随机流水模式，颜色随机，点数固定)
     * 00 (白光值 0)
     * 00 24 (点数固定值 36)
     * 00 (普通流水)
     * 07 (颜色随机值)
     * 00 0A (流水速率 10ms)
     * 00 64 (回流时间 100ms)
     * 01 2C (300ms 一次轮播)
     * 00 00 (主机刷新时间)
     */
    private static ParamTreeCmd602 build06Mock(int carouselMode) {
        return build06(carouselMode,
                0,
                36,
                0,
                7,
                10,
                100,
                300,
                0);
    }

    /**
     * 示例：【随机流水模式，点数随机，颜色固定；点数随机值 36 (表示在 36 内产生随机数)，颜色数据：R=255，G=0，B=0，W=0，普通流水，
     * 05 (随机流水模式，点数随机，颜色固定)
     * 00 24 (点数随机值 36，表示在 36以内产生随机数)
     * 00 (普通流水)
     * ff (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白光)
     * 00 0A (流水速率 10ms)
     * 00 64 (回流时间 100ms)
     * 01 2C (300ms 一次轮播)
     * 00 00 (主机刷新时间)
     */
    private static ParamTreeCmd602 build05Mock(int carouselMode) {
        return ParamTreeCmd602.build05(carouselMode,
                36,
                0,
                new RGBW(255, 0, 0, 0),
                10,
                100,
                300,
                0);
    }

    /**
     * 示例：【无痕渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
     * 04 (无痕渐变，W 固定)
     * 7D (W 值 125)
     * 05 (渐变粒度 5 级)
     * 00 (暗部保持 0)
     * 02 (亮部保持 2s)
     * 04 (4个颜色轮播)
     * 01 (红)
     * 02 (绿)
     * 03 (蓝)
     * 07 (青)
     * 05 DC (15秒一次轮播)
     */
    private static ParamTreeCmd602 build04Mock(int carouselMode) {
        return ParamTreeCmd602.build04(carouselMode,
                125,
                5,
                0,
                2,
                new int[]{1, 2, 3, 7},
                15);
    }

    /**
     * 示例 3:【呼吸渐变，W 跟随变化，保持整体亮度不变】
     * 03 (呼吸渐变，W 保持跟随变化)
     * 1E (w 最小 30)
     * FF (w 最大 255)
     * 05 (渐变粒度 5级)
     * 00 (暗部保持 0)
     * 02 (亮部保持 2s)
     * 04 (4 个颜色轮播)
     * 01 (红)
     * 02 (绿)
     * 03 (蓝)
     * 07 (青)
     * 05 DC (15 秒一次轮播)
     */
    private static ParamTreeCmd602 build03Mock(int carouselMode) {
        return ParamTreeCmd602.build03(carouselMode,
                30,
                255,
                5,
                0,
                2,
                new int[]{1, 2, 3, 7},
                15);
    }

    /**
     * 示例 2:【呼吸渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
     * 02 (呼吸渐变，W 固定)
     * 7D (W 值 125)
     * 05 (渐变粒度 5 级)
     * 00 (暗部保持 0)
     * 02 (亮部保持 2s)
     * 04 (4个颜色轮播)
     * 01 (红)
     * 02 (绿)
     * 03 (蓝)
     * 07 (青)
     * 05 DC(15秒一次轮播)
     */
    private static ParamTreeCmd602 build02Mock(int carouselMode) {
        return ParamTreeCmd602.build02(carouselMode,
                125,
                5,
                0,
                2,
                new int[]{1, 2, 3, 7},
                15);
    }

    /**
     * 示例 1-1：【整体静态轮播，1个颜色，红色，白光0】
     * 01 (静态)
     * 01 (1个轮播)
     * FF 00 00 (红色)
     * 00 (白光)
     * 0000 (轮播时间)
     * -------------------------
     * 示例 1-2：【整体静态轮播，2 个颜色，红、绿，8 秒一次轮播】
     * 01 (静态)
     * 02 (2 个轮播)
     * ff 00 00 00 (通道数据)
     * 00 ff 00 00 (通道数据)
     * 00 08(轮播时间 8s)
     */
    private static ParamTreeCmd602 build01Mock(int carouselMode) {
        List<RGBW> rgbwList = new ArrayList<>();
        rgbwList.add(new RGBW(255, 0, 0, 0));
        rgbwList.add(new RGBW(0, 255, 0, 0));
        rgbwList.add(new RGBW(0, 0, 255, 0));
        return ParamTreeCmd602.build01(carouselMode,
                1,
                rgbwList,
                15);
    }

    /**
     * 命令数据: 0x0602
     * -------------------------
     * 1. 整体静态轮播
     * -------------------------
     * 数据：
     * 轮播模式 (1字节)
     * 轮播个数 (1字节)
     * 颜色数据 (4字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：整体静态轮播模式 (RGBW)
     * 轮播个数范围：1-16
     * 轮播时间间隔：200-30000ms
     * -------------------------
     * 示例 1-1：【整体静态轮播，1个颜色，红色，白光0】
     * 01 (静态)
     * 01 (1个轮播)
     * FF 00 00 (红色)
     * 00 (白光)
     * 0000 (轮播时间)
     * -------------------------
     * 示例 1-2：【整体静态轮播，2 个颜色，红、绿，8 秒一次轮播】
     * 01 (静态)
     * 02 (2 个轮播)
     * ff 00 00 00 (通道数据)
     * 00 ff 00 00 (通道数据)
     * 00 08(轮播时间 8s)
     *
     * @param carouselMode     轮播模式
     * @param carouselNum      轮播个数
     * @param rgbws            颜色数据
     * @param carouselInterval 轮播颜色时间间隔
     * @return 结果对象
     */
    public static ParamTreeCmd602 build01(
            int carouselMode,
            int carouselNum,
            List<RGBW> rgbws,
            int carouselInterval
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setCarouselNum(carouselNum);
        paramTreeCmd602.setRgbws(rgbws);
        paramTreeCmd602.setCarouselInterval(carouselInterval);
        return paramTreeCmd602;
    }

    /**
     * -------------------------
     * 2. 整体呼吸渐变 W 固定
     * -------------------------
     * 数据：
     * 轮播模式 (1字节)
     * 白光值 (1字节)
     * 渐变粒度 (1字节)
     * 起始停留时间 (1字节)
     * 结束停留时间 (1字节)
     * 轮播个数 (1字节)
     * 颜色数据 (1字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：整体呼吸渐变，W 固定
     * 颜色数据：0-7
     * 渐变粒度：1-10 级
     * 起始、结束停留时间:0-60s
     * 轮播时间间隔：1500-30000ms
     * -------------------------
     * 示例 2:【呼吸渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
     * 02 (呼吸渐变，W 固定)
     * 7D (W 值 125)
     * 05 (渐变粒度 5 级)
     * 00 (暗部保持 0)
     * 02 (亮部保持 2s)
     * 04 (4个颜色轮播)
     * 01 (红)
     * 02 (绿)
     * 03 (蓝)
     * 07 (青)
     * 05 DC(15秒一次轮播)
     *
     * @param carouselMode        轮播模式 (1字节)
     * @param whiteLightValue     白光值 (1字节)
     * @param gradientGranularity 渐变粒度 (1字节)
     * @param startingTime        起始停留时间 (1字节)
     * @param endTime             结束停留时间 (1字节)
     * @param carouselColorList   轮播颜色列表 (1字节)
     * @param carouselInterval    轮播间隔时间 (2字节)
     * @return 结果
     */
    public static ParamTreeCmd602 build02(
            int carouselMode,
            int whiteLightValue,
            int gradientGranularity,
            int startingTime,
            int endTime,
            int[] carouselColorList,
            int carouselInterval
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setWhiteLightValue(whiteLightValue);
        return build0(gradientGranularity,
                startingTime,
                endTime,
                carouselColorList,
                carouselInterval,
                paramTreeCmd602);
    }

    /**
     * -------------------------
     * 3. 整体呼吸渐变 W 跟随变化，保持整体亮度不变
     * -------------------------
     * 数据：
     * 轮播模式 (1字节)
     * 白光最小值 (1字节)
     * 白光最大值 (1字节)
     * 渐变粒度 (1字节)
     * 起始停留时间 (1字节)
     * 结束停留时间 (1字节)
     * 轮播个数 (1字节)
     * 颜色数据 (1字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：整体呼吸渐变，W 跟随变化，保持整体亮度不变
     * 颜色数据：0-7
     * 渐变粒度：1-10 级
     * 起始、结束停留时间:0-60s
     * 轮播时间间隔：1500-30000ms
     * -------------------------
     * 示例 3:【呼吸渐变，W 跟随变化，保持整体亮度不变】
     * 03 (呼吸渐变，W 保持跟随变化)
     * 1E (w 最小 30)
     * FF (w 最大 255)
     * 05 (渐变粒度 5级)
     * 00 (暗部保持 0)
     * 02 (亮部保持 2s)
     * 04 (4 个颜色轮播)
     * 01 (红)
     * 02 (绿)
     * 03 (蓝)
     * 07 (青)
     * 05 DC (15 秒一次轮播)
     *
     * @param carouselMode        轮播模式 (1字节)
     * @param whiteLightValueMax  白光最大值 (1字节)
     * @param whiteLightValueMin  白光最小值 (1字节)
     * @param gradientGranularity 渐变粒度 (1字节)
     * @param startingTime        起始停留时间 (1字节)
     * @param endTime             结束停留时间 (1字节)
     * @param carouselColorList   颜色数据 (1字节)
     * @param carouselInterval    轮播间隔时间 (2字节)
     * @return 对象
     */
    public static ParamTreeCmd602 build03(
            int carouselMode,
            int whiteLightValueMax,
            int whiteLightValueMin,
            int gradientGranularity,
            int startingTime,
            int endTime,
            int[] carouselColorList,
            int carouselInterval
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setWhiteLightValueMax(whiteLightValueMax);
        paramTreeCmd602.setWhiteLightValueMin(whiteLightValueMin);
        return build0(gradientGranularity,
                startingTime,
                endTime,
                carouselColorList,
                carouselInterval,
                paramTreeCmd602);
    }

    /**
     * -------------------------
     * 4. 整体无痕渐变 W 固定
     * -------------------------
     * 数据：
     * 轮播模式 (1字节)
     * 白光值 (1字节)
     * 渐变粒度 (1字节)
     * 起始停留时间 (1字节)
     * 结束停留时间 (1字节)
     * 轮播个数 (1字节)
     * 颜色数据 (1字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：整体无痕渐变，W 固定
     * 颜色数据：0-7
     * 渐变粒度：1-10 级
     * 起始、结束停留时间:0-60s
     * 轮播时间间隔：1500-3000ms
     * -------------------------
     * 示例：【无痕渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒度 5 级】
     * 04 (无痕渐变，W 固定)
     * 7D (W 值 125)
     * 05 (渐变粒度 5 级)
     * 00 (暗部保持 0)
     * 02 (亮部保持 2s)
     * 04 (4个颜色轮播)
     * 01 (红)
     * 02 (绿)
     * 03 (蓝)
     * 07 (青)
     * 05 DC (15秒一次轮播)
     *
     * @param carouselMode        轮播模式 (1字节)
     * @param whiteLightValue     白光值 (1字节)
     * @param gradientGranularity 渐变粒度 (1字节)
     * @param startingTime        起始停留时间 (1字节)
     * @param endTime             结束停留时间 (1字节)
     * @param carouselColorList   颜色数据 (1字节)
     * @param carouselInterval    轮播间隔时间 (2字节)
     * @return 对象
     */
    public static ParamTreeCmd602 build04(
            int carouselMode,
            int whiteLightValue,
            int gradientGranularity,
            int startingTime,
            int endTime,
            int[] carouselColorList,
            int carouselInterval
    ) {
        return build02(carouselMode,
                whiteLightValue,
                gradientGranularity,
                startingTime,
                endTime,
                carouselColorList,
                carouselInterval);
    }

    /**
     * -------------------------
     * 5. 单个随机流水模式，点数随机，颜色固定
     * -------------------------
     * 数据：
     * 节点随机流水模式 (1字节)
     * 点数随机值 (2字节)
     * 流水模式 (1字节)
     * 颜色数据 (4字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * 主机刷新时间 (2字节)
     * -------------------------
     * 备注：单个随机流水模式，点数随机，颜色固定
     * 颜色数据：RGBW(4字节)
     * 流水模式： 0 普通流水、1 拖尾流水
     * 点数随机值范围：1-512
     * 流水速率范围：1-1000ms
     * 回流时间范围：1-1000ms
     * 轮播时间间隔：100-60000ms
     * 主机刷新时间范围：0-60000ms
     * -------------------------
     * 示例：【随机流水模式，点数随机，颜色固定；点数随机值 36 (表示在 36 内产生随机数)，颜色数据：R=255，G=0，B=0，W=0，普通流水，
     * 流水速率 10ms,回流时间100ms,轮播时间间隔：300ms 一次轮播,主机刷新时间 0】
     * 05 (随机流水模式，点数随机，颜色固定)
     * 00 24 (点数随机值 36，表示在 36以内产生随机数)
     * 00 (普通流水)
     * ff (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白光)
     * 00 0A (流水速率 10ms)
     * 00 64 (回流时间 100ms)
     * 01 2C (300ms 一次轮播)
     * 00 00 (主机刷新时间)
     *
     * @param carouselMode     节点随机流水模式 (1字节)
     * @param pointRandomValue 点数随机值 (2字节)
     * @param flowMode         流水模式 (1字节)
     * @param colorData        颜色数据 (4字节)
     * @param flowRate         流水速率 (2字节)
     * @param refluxTime       回流时间 (2字节)
     * @param carouselInterval 轮播间隔时间 (2字节)
     * @param hostRefreshTime  主机刷新时间 (2字节)
     * @return 对象
     */
    public static ParamTreeCmd602 build05(
            int carouselMode,
            int pointRandomValue,
            int flowMode,
            RGBW colorData,
            int flowRate,
            int refluxTime,
            int carouselInterval,
            int hostRefreshTime
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setPointRandomValue(pointRandomValue);
        paramTreeCmd602.setFlowMode(flowMode);
        paramTreeCmd602.setColorData(colorData);
        paramTreeCmd602.setFlowRate(flowRate);
        paramTreeCmd602.setRefluxTime(refluxTime);
        paramTreeCmd602.setCarouselInterval(carouselInterval);
        paramTreeCmd602.setHostRefreshTime(hostRefreshTime);
        return paramTreeCmd602;
    }

    /**
     * -------------------------
     * 6. 单个随机流水模式，W 固定，颜色随机，点数固定
     * -------------------------
     * 数据：
     * 节点随机流水模式 (1字节)
     * 白光值 (1字节)
     * 点数值 (2字节)
     * 流水模式 (1字节)
     * 颜色随机数据 (1字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * 主机刷新时间 (2字节)
     * -------------------------
     * 备注：单个随机流水模式，W 固定，颜色随机，点数固定
     * 颜色数据范围：0-7
     * 流水模式： 0 普通流水、1 拖尾流水
     * 点数值范围：1-512
     * 流水速率范围：1-1000ms
     * 回流时间范围：1-1000ms
     * 轮播时间间隔：100-60000ms
     * 主机刷新时间范围：0-60000ms
     * -------------------------
     * 示例 6:【随机流水模式，颜色随机，点数固定；白光值 00，点数值 36，普通流水，颜色随机数据 7 (表示 7 以内颜色随机)，
     * 流水速率 10ms, 回流时间 100ms,轮播时间间隔：300ms 一次轮播，主机刷新时间 0】
     * 06 (随机流水模式，颜色随机，点数固定)
     * 00 (白光值 0)
     * 00 24 (点数固定值 36)
     * 00 (普通流水)
     * 07 (颜色随机值)
     * 00 0A (流水速率 10ms)
     * 00 64 (回流时间 100ms)
     * 01 2C (300ms 一次轮播)
     * 00 00 (主机刷新时间)
     *
     * @param carouselMode     节点随机流水模式 (1字节)
     * @param whiteLightValue  白光值 (1字节)
     * @param pointValue       点数值 (2字节)
     * @param flowMode         流水模式 (1字节)
     * @param colorRandomData  颜色随机数据 (1字节)
     * @param flowRate         流水速率 (2字节)
     * @param refluxTime       回流时间 (2字节)
     * @param carouselInterval 轮播间隔时间 (2字节)
     * @param hostRefreshTime  主机刷新时间 (2字节)
     * @return 对象
     */
    public static ParamTreeCmd602 build06(
            int carouselMode,
            int whiteLightValue,
            int pointValue,
            int flowMode,
            int colorRandomData,
            int flowRate,
            int refluxTime,
            int carouselInterval,
            int hostRefreshTime) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setPointValue(pointValue);
        return build0(carouselMode,
                whiteLightValue,
                flowMode,
                colorRandomData,
                flowRate,
                refluxTime,
                carouselInterval,
                hostRefreshTime,
                paramTreeCmd602);
    }

    private static ParamTreeCmd602 build0(
            int carouselMode,
            int whiteLightValue,
            int flowMode,
            int colorRandomData,
            int flowRate,
            int refluxTime,
            int carouselInterval,
            int hostRefreshTime,
            ParamTreeCmd602 paramTreeCmd602) {
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setWhiteLightValue(whiteLightValue);
        paramTreeCmd602.setFlowMode(flowMode);
        paramTreeCmd602.setColorRandomData(colorRandomData);
        paramTreeCmd602.setFlowRate(flowRate);
        paramTreeCmd602.setRefluxTime(refluxTime);
        paramTreeCmd602.setCarouselInterval(carouselInterval);
        paramTreeCmd602.setHostRefreshTime(hostRefreshTime);
        return paramTreeCmd602;
    }

    /**
     * -------------------------
     * 7. 单个随机流水模式，W 固定，颜色随机，点数随机
     * -------------------------
     * 数据：
     * 节点随机流水模式 (1字节)
     * 白光值 (1字节)
     * 点数随机值 (2字节)
     * 流水模式 (1字节)
     * 颜色随机数据 (1字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * 主机刷新时间 (2字节)
     * -------------------------
     * 备注：
     * 单个随机流水模式，W 固定，颜色随机，点数随机
     * 颜色随机数据范围：0-7 流水模式： 0 普通流水、1 拖尾流水
     * 点数值随机范围：1-512
     * 流水速率范围：1-1000ms
     * 回流时间范围：1-1000ms
     * 轮播时间间隔：100-60000ms
     * 主机刷新时间范围：0-60000ms
     * -------------------------
     * 示例 7:【随机流水模式，颜色随机，点数随机；白光值 00，点数随机值 36 (表示在 36内产生随机数)，普通流水，
     * 颜色随机值 7 (表示 7 以内颜色随机)，流水速率10ms,回流时间 100ms,轮播时间间隔：300ms 一次轮播，主机刷新时间 0】
     * 07 (随机流水模式，颜色随机，点数随机)
     * 00 (白光值 0)
     * 00 24 (点数随机值36，表示在 36 内产生随机值)
     * 00 (普通流水)
     * 07 (颜色随机值，表示在 7 内产生随机颜色)
     * 00 0A (流水速率 10ms)
     * 00 64 (回流时间 100ms)
     * 01 2C (300ms 一次轮播)
     * 00 00 (主机刷新时间)
     * -------------------------
     */
    public static ParamTreeCmd602 build07(
            int carouselMode,
            int whiteLightValue,
            int pointRandomValue,
            int flowMode,
            int colorRandomData,
            int flowRate,
            int refluxTime,
            int carouselInterval,
            int hostRefreshTime
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setPointRandomValue(pointRandomValue);
        return build0(carouselMode,
                whiteLightValue,
                flowMode,
                colorRandomData,
                flowRate,
                refluxTime,
                carouselInterval,
                hostRefreshTime,
                paramTreeCmd602);
    }

    /**
     * -------------------------
     * 8. 节点流水模式
     * -------------------------
     * 数据：
     * 节点流水模式 (1字节)
     * 起始节点 (2字节)
     * 结束节点 (2字节)
     * 合并点数 (1字节)
     * 颜色数据 (4字节)
     * 底色数据 (4字节)
     * 流水速率 (2字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：
     * 节点流水模式
     * 颜色数据：RGBW(4字节)
     * 合并点数范围：1-50
     * 流水速率：50-1000ms
     * 轮播时间间隔：100-60000ms
     * -------------------------
     * 示例：【节点流水模式,起始节点 1，结束节点 255，合并点数 3，颜色数据红色，底色黑色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
     * 08 (节点流水模式)
     * 00 01 (起始节点 1)
     * 00 FF (结束节点 255)
     * 03 (合并点数)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     * 00 00 00 00 (底色黑色)
     * 00 64 (流水 11 速率 100ms)
     * 01 2C (300ms 一次轮播)
     * -------------------------
     *
     * @return
     */
    public static ParamTreeCmd602 build08(
            int carouselMode,
            int startingNode,
            int endNode,
            int mergingPoints,
            RGBW colorData,
            RGBW backgroundData,
            int flowRate,
            int carouselInterval
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setStartingNode(startingNode);
        paramTreeCmd602.setEndNode(endNode);
        paramTreeCmd602.setMergingPoints(mergingPoints);
        paramTreeCmd602.setColorData(colorData);
        paramTreeCmd602.setBackgroundData(backgroundData);
        paramTreeCmd602.setFlowRate(flowRate);
        paramTreeCmd602.setCarouselInterval(carouselInterval);
        return paramTreeCmd602;
    }

    /**
     * -------------------------
     * 9. 节点拖尾流水模式
     * -------------------------
     * 数据：
     * 节点拖尾流水模式 (1字节)
     * 起始节点 (2字节)
     * 结束节点 (2字节)
     * 合并点数 (1字节)
     * 颜色数据 (4字节)
     * 底色数据 (4字节)
     * 流水速率 (2字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：
     * 节点拖尾流水模式
     * 节点数范围：1-512
     * 颜色数据：RGBW (4字节)
     * 合并点数范围：1-50
     * 流水速率：50-1000ms
     * 轮播时间间隔：100-60000ms
     * -------------------------
     * 示例：【节点拖尾流水模式，起始节点 1，结束节点 255，合并点数 3，底色黑色，颜色数据红色，流水速率 100ms,轮播时间间隔：300ms 一次轮播】
     * 09 (节点流水模式)
     * 00 01 (起始节点 1)
     * 00 FF (结束节点 255)
     * 03 (合并点数)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     * 00 00 00 00 (底色黑色)
     * 00 64  (流水速率 100ms)
     * 01 2C (300ms 一次轮播)
     * -------------------------
     *
     * @param carouselMode     节点拖尾流水模式 (1字节)
     * @param startingNode     起始节点 (2字节)
     * @param endNode          结束节点 (2字节)
     * @param mergingPoints    合并点数 (1字节)
     * @param colorData        颜色数据 (4字节)
     * @param backgroundData   底色数据 (4字节)
     * @param flowRate         流水速率 (2字节)
     * @param carouselInterval 轮播间隔时间 (2字节)
     * @return 结果
     */
    public static ParamTreeCmd602 build09(
            int carouselMode,
            int startingNode,
            int endNode,
            int mergingPoints,
            RGBW colorData,
            RGBW backgroundData,
            int flowRate,
            int carouselInterval
    ) {
        return build08(carouselMode,
                startingNode,
                endNode,
                mergingPoints,
                colorData,
                backgroundData,
                flowRate,
                carouselInterval);
    }

    /**
     * -------------------------
     * 10. 群发流水模式
     * -------------------------
     * 数据：
     * 群发流水模式 (1字节)
     * 流水模式 (1字节)
     * 起始点数 (2字节)
     * 结束点数 (2字节)
     * 合并点数 (1字节)
     * 颜色数据 (4字节)
     * 底色数据 (4字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * -------------------------
     * 备注：群发流水模式
     * 流水模式： 0 普通流水、1 拖尾流水
     * 点数范围：1-512
     * 颜色数据：RGBW(4字节)
     * 合并点数范围：1-50
     * 流水速率：50-1000ms
     * 轮播时间间隔：100-60000ms
     * 回流时间范围：0-6000ms
     * -------------------------
     *
     * @param carouselMode     群发流水模式 (1字节)
     * @param flowMode         流水模式 (1字节)
     * @param startingNode     起始点数 (2字节)
     * @param endNode          结束点数 (2字节)
     * @param mergingPoints    合并点数 (1字节)
     * @param colorData        颜色数据 (4字节)
     * @param backgroundData   底色数据 (4字节)
     * @param flowRate         流水速率 (2字节)
     * @param refluxTime       回流时间 (2字节)
     * @param carouselInterval 轮播间隔时间 (2字节)
     * @return 对象
     */
    public static ParamTreeCmd602 build10(
            int carouselMode,
            int flowMode,
            int startingNode,
            int endNode,
            int mergingPoints,
            RGBW colorData,
            RGBW backgroundData,
            int flowRate,
            int refluxTime,
            int carouselInterval
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setFlowMode(flowMode);
        paramTreeCmd602.setStartingNode(startingNode);
        paramTreeCmd602.setEndNode(endNode);
        paramTreeCmd602.setMergingPoints(mergingPoints);
        paramTreeCmd602.setColorData(colorData);
        paramTreeCmd602.setBackgroundData(backgroundData);
        paramTreeCmd602.setFlowRate(flowRate);
        paramTreeCmd602.setRefluxTime(refluxTime);
        paramTreeCmd602.setCarouselInterval(carouselInterval);
        return paramTreeCmd602;
    }

    /**
     * -------------------------
     * 11. 节点互动模式
     * -------------------------
     * 数据：
     * 互动模式 (1字节)
     * 互动效果 (1字节)
     * 变色模式 (1字节)
     * 流水模式 (1字节)
     * 保持时间 (2字节)
     * 响应机制 (1字节)
     * 颜色数据 (4字节)
     * -------------------------
     * 备注：
     * - 互动模式
     * - 互动效果： 0 变色、1 流水
     * - 变色模式：0 直变、1 渐变
     * - 流水模式：0 普通、1 拖尾
     * - 保持时间：0-600s
     * - 响应机制：0 单个响应，1 联动响应
     * - 颜色数据：RGBW (4字节)
     * -------------------------
     * 示例：【节点互动模式，互动效果变色，变色模式直变，流水模式普通，保持时间 2s, 响应机制单个响应，颜色数据红色】
     * 1E (节点互动模式)
     * 00 (互动效果)
     * 00 (变色模式)
     * 00 (流水模式)
     * 00 02 (保持时间)
     * 00 (响应机制)
     * FF (红)
     * 00 (绿)
     * 00 (蓝)
     * 00 (白色)
     * -------------------------
     *
     * @param carouselMode      互动模式 (1字节)
     * @param interactiveEffect 互动效果 (1字节)
     * @param colorChangeMode   变色模式 (1字节)
     * @param flowMode          流水模式 (1字节)
     * @param holdTime          保持时间 (2字节)
     * @param responseMechanism 响应机制 (1字节)
     * @param colorData         颜色数据 (4字节)
     * @return 结果
     */
    public static ParamTreeCmd602 build11(
            int carouselMode,
            int interactiveEffect,
            int colorChangeMode,
            int flowMode,
            int holdTime,
            int responseMechanism,
            RGBW colorData
    ) {
        ParamTreeCmd602 paramTreeCmd602 = new ParamTreeCmd602();
        paramTreeCmd602.setCarouselMode(carouselMode);
        paramTreeCmd602.setInteractiveEffect(interactiveEffect);
        paramTreeCmd602.setColorChangeMode(colorChangeMode);
        paramTreeCmd602.setFlowMode(flowMode);
        paramTreeCmd602.setHoldTime(holdTime);
        paramTreeCmd602.setResponseMechanism(responseMechanism);
        paramTreeCmd602.setColorData(colorData);
        return paramTreeCmd602;
    }


    private static ParamTreeCmd602 build0(
            int gradientGranularity,
            int startingTime,
            int endTime,
            int[] carouselColorList,
            int carouselInterval,
            ParamTreeCmd602 paramTreeCmd602) {
        paramTreeCmd602.setGradientGranularity(gradientGranularity);
        paramTreeCmd602.setStartingTime(startingTime);
        paramTreeCmd602.setEndTime(endTime);
        paramTreeCmd602.setCarouselColorList(carouselColorList);
        paramTreeCmd602.setCarouselNum(carouselColorList.length);
        paramTreeCmd602.setCarouselInterval(carouselInterval);
        return paramTreeCmd602;
    }

    /**
     * 测试
     *
     * @param args 参数
     */
//    public static void main(String[] args) {
//
//    }

    /**
     * 【无痕渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒 度 5 级】
     * D5 C6 D5 C6(起始符)
     * -------------------
     * 06 02(命令)
     * 00 0C(数据长度 12)
     * 12 04 12 0E 09 10 03(时间)
     * 04(无痕 渐变，W 固定)
     * 7D(W 值 125)
     * 05(渐变粒度 5 级)
     * 00(起始保持时间 0)
     * 02(结束保持时间 2s)
     * 04(4 个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     */
    private String seamlessGradientWFixedMode(StringBuilder message) {
        message.append(decimalToHex(whiteLightValue));
        return breathingGradient(message).toString().toUpperCase();
    }

    /**
     * 【呼吸渐变，W 跟随变化，保持整体亮度不变】
     * D5 C6 D5 C6(起始符)06 02(命令)00 0C(数据长度 12)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 03 (呼吸渐变，W 保持跟随变化)
     * 1E(w 最小 30)
     * FF(w 最大 255)
     * 05(渐变粒 度 5 级)
     * 00(起始停留时间 0s)
     * 02(结束停留时间 2s)
     * 04(4 个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     */
    private String breathingGradientWFollowMode(StringBuilder message) {
        message.append(decimalToHex(whiteLightValueMin)).
                append(decimalToHex(whiteLightValueMax));
        return breathingGradient(message).toString().toUpperCase();
    }

    /**
     * 【呼吸渐变，W 值 125，红、绿、蓝、青，15 秒一次轮播，亮部保持 2s,渐变粒 度 5 级】
     * D5 C6 D5 C6(起始符)06 02(命令)00 0C(数据长度 12)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 02(呼吸渐变，W 固定)
     * 7D(W 值 125)
     * 05(渐变粒度 5 级)
     * 00(起始停留时间 0s)
     * 02(结束停留时间 2s)
     * 04(4个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     */
    private String breathingGradientWFixedMode(StringBuilder message) {
        message.append(decimalToHex(whiteLightValue));
        return breathingGradient(message).toString().toUpperCase();
    }

    /**
     * 渐变统一处理
     * 05(渐变粒度 5 级)
     * 00(起始停留时间 0s)
     * 02(结束停留时间 2s)
     * 04(4个颜色轮播)
     * 01(红)
     * 02(绿)
     * 03(蓝)
     * 07(青)
     * 00 0f(15 秒一次轮播)
     *
     * @param message 消息
     * @return 结果
     */
    private StringBuilder breathingGradient(StringBuilder message) {
        message.append(decimalToHex(gradientGranularity)).
                append(decimalToHex(startingTime)).
                append(decimalToHex(endTime)).
                append(decimalToHex(carouselColorList.length));
        for (int aCarouselColorList : carouselColorList) {
            message.append(decimalToHex(aCarouselColorList));
        }
        message.append(decimalToHexFF(carouselInterval));
        return message;
    }

    /**
     * 5 单个随机流水模式，点数随机，颜色固定
     * 点数随机值 (2字节)
     * 流水模式 (1字节)
     * 颜色数据 (4字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * 主机刷新时间 (2字节)
     *
     * @param message 消息
     * @return 结果
     */
    private String singleRandomFlowModePointRandomColorFixedMode(StringBuilder message) {
        message.append(decimalToHexFF(pointRandomValue)).
                append(decimalToHex(flowMode)).
                append(colorData);
        return flowMode(message).toString().toUpperCase();
    }

    /**
     * 6. 单个随机流水模式，W 固定，颜色随机，点数固定
     * 白光值 (1字节)
     * 点数值 (2字节)
     * 流水模式 (1字节)
     * 颜色随机数据 (1字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * 主机刷新时间 (2字节)
     *
     * @param message 消息
     * @return 结果
     */
    private String singleRandomFlowModeWFixedColorRandomPointFixedMode(StringBuilder message) {
        message.append(decimalToHex(whiteLightValue)).
                append(decimalToHexFF(pointValue)).
                append(decimalToHex(flowMode)).
                append(decimalToHex(colorRandomData));
        return flowMode(message).toString().toUpperCase();
    }

    /**
     * 单个随机流水模式，W 固定，颜色随机，点数随机
     * <p>
     * 白光值 (1字节)
     * 点数随机值 (2字节)
     * 流水模式 (1字节)
     * 颜色随机数据 (1字节)
     */
    private String singleRandomFlowModeWFixedColorRandomPointRandomMode(StringBuilder message) {
        message.append(decimalToHex(whiteLightValue)).
                append(decimalToHexFF(pointRandomValue)).
                append(decimalToHex(flowMode)).
                append(decimalToHex(colorRandomData));
        return flowMode(message).toString().toUpperCase();
    }

    /**
     * 流水模式统一
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     * 主机刷新时间 (2字节)
     *
     * @param message 消息
     * @return 结果
     */
    private StringBuilder flowMode(StringBuilder message) {
        message.append(decimalToHexFF(flowRate)).
                append(decimalToHexFF(refluxTime)).
                append(decimalToHexFF(carouselInterval)).
                append(decimalToHexFF(hostRefreshTime));
        return message;
    }

    /**
     * 8. 节点流水模式
     * 起始节点 (2字节)
     * 结束节点 (2字节)
     * 合并点数 (1字节)
     * 颜色数据 (4字节)
     * 底色数据 (4字节)
     * 流水速率 (2字节)
     * 轮播间隔时间 (2字节)
     */
    private String nodeFlowMode(StringBuilder message) {
        message.append(decimalToHexFF(startingNode)).
                append(decimalToHexFF(endNode)).
                append(decimalToHex(mergingPoints)).
                append(colorData).
                append(backgroundData).
                append(decimalToHexFF(flowRate)).
                append(decimalToHexFF(carouselInterval));
        return message.toString().toUpperCase();
    }

    /**
     * 9. 节点拖尾流水模式
     * 起始节点 (2字节)
     * 结束节点 (2字节)
     * 合并点数 (1字节)
     * 颜色数据 (4字节)
     * 底色数据 (4字节)
     * 流水速率 (2字节)
     * 轮播间隔时间 (2字节)
     */
    private String nodeTailingFlowMode(StringBuilder message) {
        return nodeFlowMode(message);
    }

    /**
     * 10. 群发流水模式
     * 流水模式 (1字节)
     * 起始点数 (2字节)
     * 结束点数 (2字节)
     * 合并点数 (1字节)
     * 颜色数据 (4字节)
     * 底色数据 (4字节)
     * 流水速率 (2字节)
     * 回流时间 (2字节)
     * 轮播间隔时间 (2字节)
     *
     * @param message 消息
     * @return 结果
     */
    private String massFlowPatternMode(StringBuilder message) {
        message.append(decimalToHexFF(startingNode)).
                append(decimalToHexFF(endNode)).
                append(decimalToHex(mergingPoints)).
                append(colorData).
                append(backgroundData).
                append(decimalToHexFF(flowRate)).
                append(decimalToHexFF(refluxTime)).
                append(decimalToHexFF(carouselInterval));
        return message.toString().toUpperCase();
    }

    /**
     * 11. 节点互动模式
     * -------------------------
     * 数据：
     * 互动模式 (1字节)
     * 互动效果 (1字节)
     * 变色模式 (1字节)
     * 流水模式 (1字节)
     * 保持时间 (2字节)
     * 响应机制 (1字节)
     * 颜色数据 (4字节)
     *
     * @param message 消息
     * @return 结果
     */
    private String nodeInteractionMode(StringBuilder message) {
        message.append(decimalToHex(interactiveEffect)).
                append(decimalToHex(colorChangeMode)).
                append(decimalToHex(flowMode)).
                append(decimalToHexFF(holdTime)).
                append(decimalToHex(responseMechanism)).
                append(colorData);
        return message.toString().toUpperCase();
    }

    /**
     * 【静态轮播，1 个颜色，红色，白光 0】
     * D5 C6 D5 C6(起始符)06 02(命令)00 05(数据长度 5)12 04 12 0E 09 10 03(时间)
     * -------------------
     * 01 (静态)
     * 01 (1 个轮播)
     * ff 00 00(红色)
     * 00(白光 0)
     * -------------------
     * XX(校验)13 AB 13 AB(结束符)
     */
    private String staticMode(StringBuilder message) {
        message.append(decimalToHex(rgbws.size()));
        for (RGBW rgbw : rgbws) {
            message.append(decimalToHex(rgbw.getRed()));
            message.append(decimalToHex(rgbw.getGreen()));
            message.append(decimalToHex(rgbw.getBlue()));
            message.append(decimalToHex(rgbw.getWrite()));
        }
        message.append(decimalToHexFF(carouselInterval));
        return message.toString().toUpperCase();
    }

    /**
     * 检验是否是合法的模式
     *
     * @param carouselMode 模式
     * @return 结果
     */
    private static boolean checkMode(int carouselMode) throws Exception {
        Class clazz = ParamTreeCmd602.class;
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            String name = field.getName();
            if (name.endsWith("_MODE")) {
                if (carouselMode == field.getInt(clazz)) {
                    log.info("【{} -> {}】是合法模式", carouselMode, name);
                    return true;
                }
            }
        }
        throw new Exception("非法模式");
    }
}
