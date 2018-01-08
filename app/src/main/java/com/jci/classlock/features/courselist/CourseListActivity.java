package com.jci.classlock.features.courselist;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.jci.classlock.R;
import com.jci.classlock.common.BaseActivity;
import com.jci.classlock.common.GlobalApplication;
import com.jci.classlock.greendao.gen.DaoMaster;
import com.jci.classlock.greendao.gen.DaoSession;
import com.jci.classlock.greendao.gen.LocalCourseDao;
import com.jci.classlock.model.LocalCourse;

import java.util.List;

import static com.jci.classlock.R.id.toolbar;
import static com.jci.classlock.R.id.toolbar_title;

public class CourseListActivity extends BaseActivity {

    private GridView detailCource;


    private String[][] contents;

    private AbsGridAdapter secondAdapter;

    private List<String> dataList;

    private Toolbar mToolbar;

    private TextView mTitle;

    private DaoSession daoSession;
    private DaoMaster mDaoMaster;
    private LocalCourseDao mLocalCourseDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        detailCource = (GridView)findViewById(R.id.courceDetail);
        fillStringArray();
        secondAdapter = new AbsGridAdapter(this);
        secondAdapter.setContent(contents, 6, 7);
        detailCource.setAdapter(secondAdapter);
        mToolbar = (Toolbar) this.findViewById(toolbar);
        mTitle = (TextView) this.findViewById(toolbar_title);
        mToolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle.setText("我的课表");

    }

    public void fillStringArray() {
        contents = new String[6][7];
        getStuDao();
        List<LocalCourse> list = mLocalCourseDao.queryBuilder().limit(1).list();
        LocalCourse course = list.get(0);
        int a = 0;
        int b = 0;
        switch (course.getDay()){
            case "周一":
                a = 1;
                break;
            case "周二":
                a = 2;
                break;
            case "周三":
                a = 3;
                break;
            case "周四":
                a = 4;
                break;
            case "周五":
                a = 5;
                break;
            case "周六":
                a = 6;
                break;
            case "周日":
                a = 7;
                break;
        }
        switch (course.getDay()){
            case "1-2节":
                b = 1;
                break;
            case "3-4节":
                b = 2;
                break;
            case "5-6节":
                b = 3;
                break;
            case "7-8节":
                b = 4;
                break;
            case "9-10节":
                b = 5;
                break;
            case "11-12节":
                b = 6;
                break;
        }
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                if (a == j && b == i){
                    contents[i][j] = course.getName()+"\n"+course.getRoom();

                }else {
                    contents[i][j] = "";
                }
            }
        }

    }

    /**
     * 获取StudentDao
     */
    private void getStuDao() {
        // 创建数据
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(GlobalApplication.getInstance(), "classlock.db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = mDaoMaster.newSession();
        mLocalCourseDao = daoSession.getLocalCourseDao();
    }
}
