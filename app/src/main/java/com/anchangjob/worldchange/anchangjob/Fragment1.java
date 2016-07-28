package com.anchangjob.worldchange.anchangjob;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Activity;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private int statu=0;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Data mydata;
    boolean statu=false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    Button bt_login;
    Button bt_signup;
    private OnFragmentInteractionListener mListener;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mydata=(Data)getActivity().getApplication();
        statu=mydata.islogedin;
        if(!statu) {//未登录的情况
             view=inflater.inflate(R.layout.fragment_fragment1_1, container, false);
            bt_login=(Button) view.findViewById(R.id.button9);
            bt_signup=(Button) view.findViewById(R.id.button10);
            bt_login.setOnClickListener(new Button.OnClickListener(){

                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), my_login.class);
                    startActivity(intent);
                }
            });
            bt_signup.setOnClickListener(new Button.OnClickListener(){

                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), my_register.class);
                    startActivity(intent);
                }
            });
            return view;
        }else
        {///已登录的情况

            mydata = (Data) getActivity().getApplication();

           if(mydata.user_type==0) {//个人用户
               view = inflater.inflate(R.layout.fragment_fragment1, container, false);
               TextView textview=(TextView) view.findViewById(R.id.textView11);
               textview.setText(mydata.username);
               Button bt_myfavorite = (Button) view.findViewById(R.id.button4);
               bt_myfavorite.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       Intent intent = new Intent(getActivity(), com.anchangjob.worldchange.anchangjob.mine.mine_myfavorite.class);
                       startActivity(intent);
                   }
               });

               Button bt_exit = (Button) view.findViewById(R.id.button8);
               bt_exit.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {

                       mydata.setislogedin(false);
                       mydata.fileSave(getActivity(),mydata);
                       Intent intent = new Intent(getActivity(), my_mainactivity.class);
                       startActivity(intent);
                   }
               });
               Button bt_info = (Button) view.findViewById(R.id.button6);
               bt_info.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       Intent intent = new Intent(getActivity(), com.anchangjob.worldchange.anchangjob.mine.mine_myinfoset.class);
                       startActivity(intent);
                   }
               });Button bt_system = (Button) view.findViewById(R.id.button7);
               bt_system.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       Intent intent = new Intent(getActivity(), com.anchangjob.worldchange.anchangjob.mine.mine_mysystemset.class);
                       startActivity(intent);
                   }
               });
           }
            else if(mydata.user_type==1)//公司用户
           {

               view = inflater.inflate(R.layout.fragment_fragment11, container, false);
               TextView textview=(TextView) view.findViewById(R.id.textView11_1);
               textview.setText(mydata.username);
               Button bt_sendrecruitment=(Button)view.findViewById(R.id.button4_1);
               bt_sendrecruitment.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       Intent intent = new Intent(getActivity(), com.anchangjob.worldchange.anchangjob.recruitment_push.class);
                       startActivity(intent);
                   }
               });
               Button bt_exit = (Button) view.findViewById(R.id.button8_1);
               bt_exit.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       mydata.setislogedin(false);
                       mydata.fileSave(getActivity(),mydata);
                       Intent intent = new Intent(getActivity(), my_mainactivity.class);
                       startActivity(intent);
                   }
               });
               Button bt_company = (Button) view.findViewById(R.id.button6_1);
               bt_company.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       mydata = (Data) getActivity().getApplication();
                       mydata.setislogedin(false);
                       Intent intent = new Intent(getActivity(), com.anchangjob.worldchange.anchangjob.mine.mine_mycompany.class);
                       startActivity(intent);
                   }
               });
               Button bt_system = (Button) view.findViewById(R.id.button7_1);
               bt_system.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {
                       mydata = (Data) getActivity().getApplication();
                       mydata.setislogedin(false);
                       Intent intent = new Intent(getActivity(), com.anchangjob.worldchange.anchangjob.mine.mine_mysystemset.class);
                       startActivity(intent);
                   }
               });

           }
        return view;}
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override///////////////////////////因为版本问题注释掉的
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
