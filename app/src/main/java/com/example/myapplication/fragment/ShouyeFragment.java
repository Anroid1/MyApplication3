package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * shouye
 */
public class  ShouyeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_shouye, container, false);
//定义数据
        int imgs[] = {R.drawable.qingcai1, R.drawable.lizi};
        //封装数据
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length; i++) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("img", imgs[i]);
            items.add(data);
        }
        //定义适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), items, R.layout.shouyeshangpin_item,
                new String[]{"img"}, new int[]{R.id.iv_img});
        GridView gridView = view.findViewById(R.id.gv);
        gridView.setAdapter(simpleAdapter);

        return view;
    }


}