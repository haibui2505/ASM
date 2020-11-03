package com.example.pk01537_duy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link phoBien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class phoBien extends Fragment {

    TextView tendangnhap;
    LinearLayout  baihat, lil_taoBaiHat;
    DatabaseHelper db;
    SharedPreferences sharedPreferences, sharedPreferencesID;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public phoBien() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChu_Frag.
     */
    // TODO: Rename and change types and number of parameters
    public static phoBien newInstance(String param1, String param2) {
        phoBien fragment = new phoBien();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pho_bien_frg, container, false);
        tendangnhap = view.findViewById(R.id.edittext_tenNguoiDung);

        final Intent intent = getActivity().getIntent();
        final String myname = intent.getStringExtra("hvt");
        final Integer myId = intent.getIntExtra("id", 1);
        final String myStringId = myId + "";

        sharedPreferences = getActivity().getSharedPreferences("tennguoidung", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ten", myname + "");
        editor.commit();

        SharedPreferences sharedPreferencesID = getActivity().getSharedPreferences("IDnguoidung", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferencesID.edit();
        editor1.putString("id", myStringId);
        editor1.commit();

        tendangnhap.setText(sharedPreferences.getString("ten", "") + " đã đăng nhập");
        tendangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Bạn muốn xác nhận đăng xuất?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("name");
                        editor.remove("pass");
                        editor.remove("check");
                        editor.commit();
                        getActivity().onBackPressed();
                    }
                });
                builder.setNegativeButton("Hủy!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        db = new DatabaseHelper(getActivity(), "new.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS BaiHat (Id INTEGER PRIMARY KEY AUTOINCREMENT, TenBaiHat VARCHAR(30), TacGia VARCHAR(100), LoiBaiHat VARCHAR(100), NgayGui VARCHAR(200)) ");

        lil_taoBaiHat = view.findViewById(R.id.lil_taoDon);
        baihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new babihat_Frag();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        fragment).commit();
            }
        });
        lil_taoBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),TaoBaiHat.class));
            }
        });

        return view;
    }


}