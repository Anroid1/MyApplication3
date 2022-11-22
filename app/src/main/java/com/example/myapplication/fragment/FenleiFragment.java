package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.ShangpinActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FenleiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FenleiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FenleiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FenleiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FenleiFragment newInstance(String param1, String param2) {
        FenleiFragment fragment = new FenleiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fenlei, container, false);
        ExpandableListView exlist_type=view.findViewById(R.id.exlist_type);
        ExpandableListAdapter adapter=new BaseExpandableListAdapter() {
            //定义数据
            String[] typeName = new String[]{"水果类","蔬菜类"};
            String[][] itemName = new String[][]{
                    {"牧疆山新疆库尔勒大红香酥梨",
             "四川爱媛38号果冻橙手剥橙子",
             "攀枝花凯特芒果"},
             {"豌豆新鲜云南高原带壳水果豌豆荚",
             "普罗旺斯西红柿"}};
            int[] img = new int[]{R.drawable.lizi140x100, R.drawable.juzi140x100, R.drawable.mangguo1140x100,R.drawable.wandou140x100, R.drawable.fanqie3140x100};

            @Override
            public int getGroupCount() {
                return typeName.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return itemName[i].length;
            }

            @Override
            public Object getGroup(int i) {
                //获取父项组数据
                return typeName[i];
            }

            @Override
            public Object getChild(int i, int i1) {
                //获得子项数据
                return itemName[i][i1];
            }

            @Override
            public long getGroupId(int i) {
                //获得组id
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                //获得子项id
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                //定义一个线性布局
                LinearLayout layout = new LinearLayout(getActivity());
                //设置线性布局为水平排列
                layout.setOrientation(LinearLayout.HORIZONTAL);

                //定义一个文本控件
                TextView textView1 = getTextView1();
                textView1.setText(getGroup(i).toString());
                layout.addView(textView1);
                return layout;
            }
            //定义一个文本控件
            private TextView getTextView1() {
                AbsListView.LayoutParams lp1 = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                TextView textView1 = new TextView(getActivity());
                textView1.setLayoutParams(lp1);
                textView1.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView1.setPadding(80, 0, 0, 0);
                textView1.setTextSize(28);
                return textView1;
            }
            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                //定义一个线性布局
                LinearLayout layout1 = new LinearLayout(getActivity());
                //设置线性布局为水平排列
                layout1.setOrientation(LinearLayout.HORIZONTAL);
                //定义一个图片
                ImageView imageView = new ImageView(getActivity());
                //设置图片
                imageView.setImageResource(img[i1]);
                //将图片控件添加到线性布局
                layout1.addView(imageView);
                TextView textView = getTextView();
                textView.setText(getChild(i, i1).toString());
                layout1.addView(textView);
                return layout1;
            }

            //定义一个文本控件
            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                TextView textView = new TextView(getActivity());
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(10, 20, 0, 0);
                textView.setTextSize(20);
                return textView;
            }


            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }

        };
        //将适配器添加到List View
        exlist_type.setAdapter(adapter);
        exlist_type.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent=new Intent(getActivity(),ShangpinActivity.class);
                intent.putExtra("gid",i);
                intent.putExtra("gid",i1);
                startActivity(intent);
                return true;
            }
        });
        return view;
    }
}