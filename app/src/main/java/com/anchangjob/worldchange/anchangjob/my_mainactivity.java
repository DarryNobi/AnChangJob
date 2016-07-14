package com.anchangjob.worldchange.anchangjob;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 *
 * @author guolin
 */
public class my_mainactivity extends Activity implements View.OnClickListener {

    /**
     * 用于展示招聘信息的Fragment
     */
    private Fragment1 jobFragment;

    /**
     * 用于展示聊天记录的Fragment
     */
    private Fragment2 comFragment;

    /**
     * 用于展示个人信息的Fragment
     */
    private Fragment3 infoFragment;

    /**
     * 消息招聘信息布局
     */
    private View jobLayout;

    /**
     * 聊天记录布局
     */
    private View comLayout;

    /**
     * 个人信息布局
     */
    private View infoLayout;


    /**
     * 在Tab布局上显示招聘信息图标的控件
     */
    private ImageView jobImage;

    /**
     * 在Tab布局上显示聊天记录图标的控件
     */
    private ImageView comImage;

    /**
     * 在Tab布局上显示个人信息的控件
     */
    private ImageView infoImage;

    /**
     * 在Tab布局上显示招聘信息的控件
     */
    private TextView jobText;

    /**
     * 在Tab布局上显示聊天记录的控件
     */
    private TextView comText;

    /**
     * 在Tab布局上显示个人信息的控件
     */
    private TextView infoText;


    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_mainactivity);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }
    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        jobLayout = findViewById(R.id.job_layout);
        comLayout = findViewById(R.id.com_layout);
        infoLayout = findViewById(R.id.info_layout);
        jobImage = (ImageView) findViewById(R.id.job_image);
        comImage = (ImageView) findViewById(R.id.com_image);
        infoImage = (ImageView) findViewById(R.id.info_image);
        jobText = (TextView) findViewById(R.id.job_text);
        comText = (TextView) findViewById(R.id.com_text);
        infoText = (TextView) findViewById(R.id.info_text);
        jobLayout.setOnClickListener(this);
        comLayout.setOnClickListener(this);
        infoLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.job_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.com_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.info_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                jobImage.setImageResource(R.drawable.job);
                jobText.setTextColor(Color.BLACK);
                if (jobFragment == null) {
                    // 如果jobFragment为空，则创建一个并添加到界面上
                    jobFragment = new Fragment1();
                    transaction.add(R.id.content, jobFragment);
                } else {
                    // 如果jobFragment不为空，则直接将它显示出来
                    transaction.show(jobFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                comImage.setImageResource(R.drawable.com);
                comText.setTextColor(Color.BLACK);
                if (comFragment == null) {
                    // 如果comFragment为空，则创建一个并添加到界面上
                    comFragment = new Fragment2();
                    transaction.add(R.id.content, comFragment);
                } else {
                    // 如果comFragment不为空，则直接将它显示出来
                    transaction.show(comFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                infoImage.setImageResource(R.drawable.info);
                infoText.setTextColor(Color.BLACK);
                if (infoFragment == null) {
                    // 如果infoFragment为空，则创建一个并添加到界面上
                    infoFragment = new Fragment3();
                    transaction.add(R.id.content,infoFragment);
                } else {
                    // 如果infoFragment不为空，则直接将它显示出来
                    transaction.show(infoFragment);
                }
                break;

        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        jobImage.setImageResource(R.drawable.job_u);
        jobText.setTextColor(Color.parseColor("#82858b"));
        comImage.setImageResource(R.drawable.com_u);
        comText.setTextColor(Color.parseColor("#82858b"));
        infoImage.setImageResource(R.drawable.info_u);
        infoText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (jobFragment != null) {
            transaction.hide(jobFragment);
        }
        if (comFragment != null) {
            transaction.hide(comFragment);
        }
        if (infoFragment != null) {
            transaction.hide(infoFragment);
        }
    }
}
