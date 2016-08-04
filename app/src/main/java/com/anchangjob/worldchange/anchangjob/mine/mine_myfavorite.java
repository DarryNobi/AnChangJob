package com.anchangjob.worldchange.anchangjob.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Toast;

import com.anchangjob.worldchange.anchangjob.Data;
import com.anchangjob.worldchange.anchangjob.Fragment3;
import com.anchangjob.worldchange.anchangjob.MyItemRecyclerViewAdapter;
import com.anchangjob.worldchange.anchangjob.R;
import com.anchangjob.worldchange.anchangjob.dummy.DummyContent;
import com.anchangjob.worldchange.anchangjob.recruitment_detail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class mine_myfavorite extends Activity {
    Data mydata;
    JSONArray my_jsonarry;
    Fragment3.OnListFragmentInteractionListener mListener;

    private int mColumnCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);

        LayoutInflater iInflater = LayoutInflater.from(getBaseContext());
        View view = iInflater.inflate(R.layout.fragment_item_list, null);



        //final View view=(View)findViewById(R.layout.fragment_item_list);
       // RecyclerView view =(RecyclerView) findViewById(R.layout.fragment_item_list);
        JSONObject my_recruitments=getdata();

        try {
            my_jsonarry = my_recruitments.getJSONArray("response");
            DummyContent.clean();
            for (int i = 0; i < my_jsonarry.length(); i++) {
                JSONObject temp = (JSONObject) my_jsonarry.get(i);
                DummyContent.DummyItem dummyItem=new DummyContent.DummyItem(temp);
                DummyContent.addItem(dummyItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // if (view instanceof RecyclerView) {
           // Context context = view.getContext();/////////////////////
            Context context=getBaseContext();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);


            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                int fstview=-1;

                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    this.onScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
                    fstview=firstVisibleItem;
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView,
                                                 int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    if (newState== NumberPicker.OnScrollListener.SCROLL_STATE_IDLE&&fstview==0)
                        Toast.makeText(mine_myfavorite.this, "刷新测试", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }

            });


            mListener=new Fragment3.OnListFragmentInteractionListener(){
                @Override
                public void onListFragmentInteraction(DummyContent.DummyItem item) {

                }

                @Override
                public void onItemClick(MyItemRecyclerViewAdapter.ViewHolder item, int position) {

                    Toast.makeText(mine_myfavorite.this, "点击事件测试", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mine_myfavorite.this,recruitment_detail.class);
                    Bundle bundle = new Bundle();
                    int temp= 0;
                    try {
                        temp = ((JSONObject) my_jsonarry.get(position)).getInt("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    bundle.putInt("recruitment",temp );
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            };

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        //}

        mydata=(Data)getApplication();

    }


    JSONObject getdata(){
        JSONObject jsonresult=new JSONObject();
        mydata=(Data)getApplication();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"user/get_collect/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("uid", Integer.toString(mydata.userid)));
        try {
            entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        // 3.客户端带着请求对象请求服务器端
        try {
            // 服务器端返回请求的数据
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 解析请求返回的数据
            if (httpResponse != null
                    && httpResponse.getStatusLine().getStatusCode() == 200) {
                String element = EntityUtils.toString(httpResponse.getEntity(),
                        HTTP.UTF_8);
                if (element.startsWith("{")) {
                    try {
                        jsonresult= new JSONObject(element);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonresult;
    }

}
