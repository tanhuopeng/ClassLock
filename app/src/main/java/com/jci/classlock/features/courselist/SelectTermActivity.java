package com.jci.classlock.features.courselist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jci.classlock.R;
import com.jci.classlock.common.ConstantValues;
import com.jci.classlock.common.GlobalApplication;
import com.jci.classlock.greendao.gen.DaoMaster;
import com.jci.classlock.greendao.gen.DaoSession;
import com.jci.classlock.greendao.gen.LocalCourseDao;
import com.jci.classlock.model.CourseInfo;
import com.jci.classlock.model.LocalCourse;
import com.jci.classlock.utils.SPUtils;
import com.jci.classlock.utils.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.jci.classlock.R.id.toolbar;
import static com.jci.classlock.R.id.toolbar_title;

public class SelectTermActivity extends AppCompatActivity {

    private TextView mTitle;
    private Toolbar mToolbar;
    private RadioGroup mRadioGroup;
    private Button mBtn;
    private DaoSession daoSession;
    private DaoMaster mDaoMaster;
    private LocalCourseDao mLocalCourseDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_term);
        mTitle = (TextView) this.findViewById(toolbar_title);
        mTitle.setText("选择学期");
        mBtn = (Button) this.findViewById(R.id.mSelectTerm_Btn);
        mToolbar = (Toolbar) this.findViewById(toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
        mRadioGroup = (RadioGroup) this.findViewById(R.id.mSecletTermActivity_RadioGrop);
        initEvent();
    }

    private void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                RadioButton radioButton = (RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());
                final String s = radioButton.getText().toString();
                ConstantValues.Term = s;
                if (radioButton.getText().toString() != null){
                    BmobQuery<CourseInfo> bmobQuery = new BmobQuery<CourseInfo>();
                    bmobQuery.findObjects(new FindListener<CourseInfo>() {

                        @Override
                        public void done(List<CourseInfo> list, BmobException e) {
                            // TODO: 2017/11/13 0013 一些事情 
                            for (CourseInfo c : list
                                 ) {
                                if (c.getClasses().equals(SPUtils.getInstance().getString("classes"))&&
                                        c.getTerm().equals(s)){
                                    LocalCourse course = new LocalCourse();
                                    course.setName(c.getName());
                                    course.setClasses(c.getClasses());
                                    course.setDay(c.getDay());
                                    course.setTerm(c.getTerm());
                                    course.setRoom(c.getRoom());
                                    course.setStart(c.getStart());
                                    course.setTime(c.getTime());
                                    course.setWeek(c.getWeek());
                                    course.setTeacher(c.getTeacher());
                                    getStuDao();
                                    mLocalCourseDao.insert(course);
                                    ToastUtils.showShort("导入成功");
                                    SPUtils.getInstance().put("haveCourse",true);
                                }
                            }
                        }
                    });

                }else {
                    ToastUtils.showShort("没有录入该课程");
                }
            }
        });
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
