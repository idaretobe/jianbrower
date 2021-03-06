/*
 * Created By WeihuaGu (email:weihuagu_work@163.com)
 * Copyright (c) 2017
 * All right reserved.
 */

package weihuagu.com.jian.ui.view;
import android.widget.PopupWindow;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import weihuagu.com.jian.R;

public class ItemLongClickedPopWindow extends PopupWindow{

    private LayoutInflater itemLongClickedPopWindowInflater;
    private View itemLongClickedPopWindowView;
    private Context context;

    private int type;


    /**
     * 书签条目弹出菜单
     * @value
     * {@value}
     * */
    public static final int FAVORITES_ITEM_POPUPWINDOW = 0;
    /**
     * 书签页面弹出菜单
     * @value
     * {@value}
     * */
    public static final int FAVORITES_VIEW_POPUPWINDOW = 1;
    /**
     * 历史条目弹出菜单
     * @value
     * {@value}
     * */
    public static final int HISTORY_ITEM_POPUPWINDOW = 3;
    /**
     * 历史页面弹出菜单
     * @value
     * {@value}
     * */
    public static final int HISTORY_VIEW_POPUPWINDOW = 4;
    /**
     * 图片项目弹出菜单
     * @value
     * {@value}
     * */
    public static final int IMAGE_VIEW_POPUPWINDOW = 5;
    /**
     * 超链接项目弹出菜单
     * @value
     * {@value}
     * */

    public static final int ATTR_SHOW=6;

    public static final int SRC_ANCHOR_TYPE=7;

    public ItemLongClickedPopWindow(Context context, int type, int width, int height){
        super(context);
        this.context = context;
        this.type = type;

        //创建
        this.initTab();

        //设置默认选项
        setWidth(width);
        setHeight(height);
        setContentView(this.itemLongClickedPopWindowView);
        setOutsideTouchable(true);
        setFocusable(true);
    }

    //实例化
    private void initTab(){
        this.itemLongClickedPopWindowInflater = LayoutInflater.from(this.context);
        switch(type){
            case SRC_ANCHOR_TYPE:
                this.itemLongClickedPopWindowView = this.itemLongClickedPopWindowInflater.inflate(R.layout.longclickedpopinlink, null);
                break;
            case FAVORITES_ITEM_POPUPWINDOW:
                this.itemLongClickedPopWindowView = this.itemLongClickedPopWindowInflater.inflate(R.layout.longclickedpop, null);
                break;
            case FAVORITES_VIEW_POPUPWINDOW:
                //对于书签内容弹出菜单，未作处理
                break;
            case HISTORY_ITEM_POPUPWINDOW:
                this.itemLongClickedPopWindowView = this.itemLongClickedPopWindowInflater.inflate(R.layout.longclickedpop, null);
                break;
            case HISTORY_VIEW_POPUPWINDOW:
                //对于历史内容弹出菜单，未作处理
                break;
            case IMAGE_VIEW_POPUPWINDOW:
                //图片
                this.itemLongClickedPopWindowView = this.itemLongClickedPopWindowInflater.inflate(R.layout.longclickedpop, null);
                break;
            case ATTR_SHOW:
                //属性展示
                this.itemLongClickedPopWindowView = this.itemLongClickedPopWindowInflater.inflate(R.layout.attrshow, null);
                break;
        }

    }


    public View getView(int id){

        return this.itemLongClickedPopWindowView.findViewById(id);
    }


}
