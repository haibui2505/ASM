package com.example.pk01537_duy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link listbaihattt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listbaihattt extends Fragment {

    DatabaseHelper db;
    ListView listView;
    ArrayList<BaiHat> arrayList;
    baiHatAdapter adapter;
    int index = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public listbaihattt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listguihang_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static listbaihattt newInstance(String param1, String param2) {
        listbaihattt fragment = new listbaihattt();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listbaihat, container, false);

        listView = view.findViewById(R.id.lv_guiHang);
        arrayList = new ArrayList<>();
        adapter = new baiHatAdapter(getActivity(), R.layout.listview_bai_hat, arrayList);
        listView.setAdapter(adapter);
        db = new DatabaseHelper(getActivity(), "new.sqlite", null, 1);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                final int a = arrayList.get(index).getId();
                final String name = arrayList.get(index).getTen();
                final String tac = arrayList.get(index).getTac();
                final String loi = arrayList.get(index).getLoi();
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setGravity(Gravity.RIGHT);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menuXoa:
                                xoaBaiHat(a);
                                break;
                            case R.id.menuSua:
                                suaBaiHat(a, name, tac, loi);
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();

                return false;
            }
        });
        getBaiHat();
        return view;
    }

    private void getBaiHat() {
        Cursor cursorCheck = db.GetData("SELECT * FROM BaiHat");
        if (cursorCheck.getCount() == 0) {

        } else {
            Cursor cursor = db.GetData("SELECT * FROM BaiHat ORDER BY Id DESC");
            arrayList.clear();
            while (cursor.moveToNext()) {

                int idnv = cursor.getInt(0);
                String ten = cursor.getString(1);
                String tac = cursor.getString(2);
                String loi = cursor.getString(3);
                String ngaygui = cursor.getString(4);

                Log.d("duy", idnv + " " + ten + " " + tac + " " + loi + " " + ngaygui);

                arrayList.add(new BaiHat(idnv, ten, tac, loi, ngaygui));
            }
            adapter.notifyDataSetChanged();
        }
    }

    public void xoaBaiHat(final int id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(getActivity());
        dialogXoa.setMessage("Xác nhận xóa bài hát này?");
        dialogXoa.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                db.QueryData("DELETE FROM BaiHat  WHERE Id = '" + id + "'");
                getBaiHat();
            }
        });
        dialogXoa.setNegativeButton("Hủy!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }

    public void suaBaiHat(final int id, final String ten, final String tacc, final String loii) {
        final Dialog dialog = new Dialog(getActivity(), R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.suabaihat);
        final EditText tenbaihat = dialog.findViewById(R.id.edt_tenBaiHat);
        final EditText tac = dialog.findViewById(R.id.edt_tacGia);
        final EditText loi = dialog.findViewById(R.id.edt_loiBaiHat);
        Button btnXacNhan = dialog.findViewById(R.id.btn_suaGuiHang);
        tenbaihat.setText(ten);
        tac.setText(tacc);
        loi.setText(loii);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String tenMoi = tenbaihat.getText().toString().trim();
                String tacMoi = tac.getText().toString().trim();
                String loiMoi = loi.getText().toString().trim();

                db.QueryData("UPDATE BaiHat SET TenBaiHat = '" + tenMoi + "', TacGia = '" + tacMoi + "', LoiBaiHat = '" + loiMoi + "' Where Id = '" + id + "'");
                Toast.makeText(getActivity(), "Chúc mừng bạn thêm nghiệp thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                getBaiHat();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();

    }
}