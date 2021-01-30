package com.example.py7.DISJA;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.py7.DISJA.adapters.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView TvStatus;
    Button BtnProses;
    EditText TxID, TxNamaPelamar, TxNamaPekerjaan, TxLamar, TxPersetujuan, TxStatus;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        TxID = (EditText)findViewById(R.id.txID);
        TxNamaPelamar = (EditText)findViewById(R.id.txNamaPelamar);
        TxNamaPekerjaan = (EditText)findViewById(R.id.txPekerjaan);
        TxLamar = (EditText)findViewById(R.id.txMelamar);
        TxPersetujuan = (EditText)findViewById(R.id.txPersetujuan);
        TxStatus = (EditText)findViewById(R.id.txStatus);

        TvStatus = (TextView)findViewById(R.id.tVStatus);
        BtnProses = (Button)findViewById(R.id.btnProses);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        getData();

        TxPersetujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        BtnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesKembali();
            }
        });

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
    }

    private void prosesKembali() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setMessage("Proses ke persetujuan pelamaran?");
        builder.setCancelable(true);
        builder.setPositiveButton("Proses", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idpinjam = TxID.getText().toString().trim();
                String kembali = "Disetujui";

                ContentValues values = new ContentValues();

                values.put(DBHelper.row_status, kembali);
                dbHelper.updateData(values, id);
                Toast.makeText(AddActivity.this, "Proses Pelamaran Berhasil", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TxPersetujuan.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void getData() {
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String tglMelamar = sdf1.format(c1.getTime());
        TxLamar.setText(tglMelamar);

        Cursor cur = dbHelper.oneData(id);
        if(cur.moveToFirst()){
            String idmelamar = cur.getString(cur.getColumnIndex(DBHelper.row_id));
            String namaPekerja = cur.getString(cur.getColumnIndex(DBHelper.row_nama));
            String Pekerjaan = cur.getString(cur.getColumnIndex(DBHelper.row_judul));
            String lamar = cur.getString(cur.getColumnIndex(DBHelper.row_pinjam));
            String persetujuan = cur.getString(cur.getColumnIndex(DBHelper.row_kembali));
            String status = cur.getString(cur.getColumnIndex(DBHelper.row_status));

            TxID.setText(idmelamar);
            TxNamaPelamar.setText(namaPekerja);
            TxNamaPekerjaan.setText(Pekerjaan);
            TxLamar.setText(lamar);
            TxPersetujuan.setText(persetujuan);
            TxStatus.setText(status);

            if (TxID.equals("")){
                TvStatus.setVisibility(View.GONE);
                TxStatus.setVisibility(View.GONE);
                BtnProses.setVisibility(View.GONE);
            }else{
                TvStatus.setVisibility(View.VISIBLE);
                TxStatus.setVisibility(View.VISIBLE);
                BtnProses.setVisibility(View.VISIBLE);
            }

            if(status.equals("Dilamar")){
                BtnProses.setVisibility(View.VISIBLE);
            }else {
                BtnProses.setVisibility(View.GONE);
                TxNamaPelamar.setEnabled(false);
                TxNamaPekerjaan.setEnabled(false);
                TxPersetujuan.setEnabled(false);
                TxStatus.setEnabled(false);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        String idpinjam = TxID.getText().toString().trim();
        String status = TxStatus.getText().toString().trim();

        MenuItem itemDelete = menu.findItem(R.id.action_delete);
        MenuItem itemClear = menu.findItem(R.id.action_clear);
        MenuItem itemSave = menu.findItem(R.id.action_save);

        if (idpinjam.equals("")){
            itemDelete.setVisible(false);
            itemClear.setVisible(true);
        }else {
            itemDelete.setVisible(true);
            itemClear.setVisible(false);
        }

        if(status.equals("Disetujui")){
            itemSave.setVisible(false);
            itemDelete.setVisible(false);
            itemClear.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                insertAndUpdate();
        }
        switch (item.getItemId()){
            case R.id.action_clear:
                TxNamaPelamar.setText("");
                TxNamaPekerjaan.setText("");
                TxPersetujuan.setText("");
        }
        switch (item.getItemId()){
            case R.id.action_delete:
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                builder.setMessage("Data ini akan dihapus");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteData(id);
                        Toast.makeText(AddActivity.this, "Terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertAndUpdate(){
        String idpinjam = TxID.getText().toString().trim();
        String nama = TxNamaPelamar.getText().toString().trim();
        String judul = TxNamaPekerjaan.getText().toString().trim();
        String tglPinjam = TxLamar.getText().toString().trim();
        String tglKembali = TxPersetujuan.getText().toString().trim();
        String status = "Disetujui";

        ContentValues values = new ContentValues();

        values.put(DBHelper.row_nama, nama);
        values.put(DBHelper.row_judul, judul);
        values.put(DBHelper.row_kembali, tglKembali);
        values.put(DBHelper.row_status, status);

        if (nama.equals("") || judul.equals("") || tglKembali.equals("")){
            Toast.makeText(AddActivity.this, "Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
        }else {
            if(idpinjam.equals("")){
                values.put(DBHelper.row_pinjam, tglPinjam);
                dbHelper.insertData(values);
            }else {
                dbHelper.updateData(values, id);
            }

            Toast.makeText(AddActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
