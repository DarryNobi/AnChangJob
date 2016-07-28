package com.anchangjob.worldchange.anchangjob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.anchangjob.worldchange.anchangjob.dummy.DummyContent;
import com.anchangjob.worldchange.anchangjob.dummy.DummyContent.DummyItem;

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

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class Fragment3 extends android.app.Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    Data mydata;
    JSONArray my_jsonarry;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Fragment3() {
    }
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Fragment3 newInstance(int columnCount) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
   @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        JSONObject my_recruitments=getdata();

        try {
            my_jsonarry = my_recruitments.getJSONArray("response");
            DummyContent.clean();
            for (int i = 0; i < my_jsonarry.length(); i++) {
                JSONObject temp = (JSONObject) my_jsonarry.get(i);
                DummyItem dummyItem=new DummyItem(temp);
                DummyContent.addItem(dummyItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;


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
                        Toast.makeText(getActivity(), "刷新测试", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }

            });


            mListener=new OnListFragmentInteractionListener(){
                @Override
                public void onListFragmentInteraction(DummyItem item) {

                }

                @Override
                public void onItemClick(MyItemRecyclerViewAdapter.ViewHolder item, int position) {

                    Toast.makeText(getActivity(), "点击事件测试", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),recruitment_detail.class);
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

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }

        mydata=(Data) getActivity().getApplication();
        return view;
    }
/*///////////////////////////因为版本问题注释掉的
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener{
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
        void onItemClick(MyItemRecyclerViewAdapter.ViewHolder item, int position);
        //Toast.makeText(this.my_login, "密码不能为空", Toast.LENGTH_SHORT).show();

    }


    public void onListFragmentInteraction(DummyItem item){
        Toast.makeText(getActivity(), "点击事件测试", Toast.LENGTH_SHORT).show();
    }
    /*////////////////////不知道可不可以用
    public void onListItemClick(ListView l, View v, int position, long id) {
        //showDetails(position);
    }*/
    JSONObject getdata(){
        JSONObject jsonresult=new JSONObject();
        mydata=(Data)getActivity().getApplication();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"user/recruitment_pull/");
        // post请求方式数据放在实体类中
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
