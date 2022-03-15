package edu.aku.hassannaqvi.tpvics_r2.ui.sections;

import static edu.aku.hassannaqvi.tpvics_r2.core.MainApp.child;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import edu.aku.hassannaqvi.tpvics_r2.R;
import edu.aku.hassannaqvi.tpvics_r2.contracts.TableContracts;
import edu.aku.hassannaqvi.tpvics_r2.core.MainApp;
import edu.aku.hassannaqvi.tpvics_r2.database.DatabaseHelper;
import edu.aku.hassannaqvi.tpvics_r2.databinding.ActivitySectionImBinding;
import edu.aku.hassannaqvi.tpvics_r2.ui.TakePhoto;

public class SectionIMActivity extends AppCompatActivity {


    private static final String TAG = "SectionIMActivity";
    ActivitySectionImBinding bi;
    String[] deff = {"44", "66", "88", "97"};
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_im);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        bi.setForm(child);
        setupSkips();
        if (MainApp.superuser) bi.btnContinue.setText("Review Next");
    }


    private void setupSkips() {

        bi.im04yy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bi.im04yy.getText().toString().isEmpty()) return;
                bi.im04mm.setMaxvalue(Integer.parseInt(bi.im04yy.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR) ?
                        Calendar.getInstance().get(Calendar.MONTH) + 1 : 12f);
                bi.im04dd.setMaxvalue(Integer.parseInt(bi.im04yy.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR)
                        && Integer.parseInt(bi.im04mm.getText().toString()) == Calendar.getInstance().get(Calendar.MONTH) + 1 ?
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) : 31f);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bi.im04mm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bi.im04yy.getText().toString().isEmpty() || bi.im04mm.getText().toString().isEmpty())
                    return;
                bi.im04dd.setMaxvalue(Integer.parseInt(bi.im04yy.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR)
                        && Integer.parseInt(bi.im04mm.getText().toString()) == Calendar.getInstance().get(Calendar.MONTH) + 1 ?
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) : 31f);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        setDefault(bi.im0501dd, bi.im0501mm, bi.im0501yy);
        setDefault(bi.im0502dd, bi.im0502mm, bi.im0502yy);
        setDefault(bi.im0503dd, bi.im0503mm, bi.im0503yy);
        setDefault(bi.im0504dd, bi.im0504mm, bi.im0504yy);
        setDefault(bi.im0505dd, bi.im0505mm, bi.im0505yy);
        setDefault(bi.im0506dd, bi.im0506mm, bi.im0506yy);
        setDefault(bi.im0507dd, bi.im0507mm, bi.im0507yy);
        setDefault(bi.im0508dd, bi.im0508mm, bi.im0508yy);
        setDefault(bi.im0509dd, bi.im0509mm, bi.im0509yy);
        setDefault(bi.im0510dd, bi.im0510mm, bi.im0510yy);
        setDefault(bi.im0510add, bi.im0510amm, bi.im0510ayy);
        setDefault(bi.im0511dd, bi.im0511mm, bi.im0511yy);
        setDefault(bi.im0512dd, bi.im0512mm, bi.im0512yy);
        setDefault(bi.im0513dd, bi.im0513mm, bi.im0513yy);
        setDefault(bi.im0514dd, bi.im0514mm, bi.im0514yy);
        setDefault(bi.im0515dd, bi.im0515mm, bi.im0515yy);
        setDefault(bi.im0516dd, bi.im0516mm, bi.im0516yy);
        setDefault(bi.im0517dd, bi.im0517mm, bi.im0517yy);
    }


    private void setDefault(EditTextPicker day, EditTextPicker mon, EditTextPicker year) {
        day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (day.getText().toString().isEmpty()) return;
                mon.setEnabled(true);
                year.setEnabled(true);
                if (Arrays.asList(deff).contains(day.getText().toString())) {
                    day.setRangedefaultvalue(Float.parseFloat(day.getText().toString()));
                    mon.setEnabled(false);
                    year.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        year.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (year.getText().toString().isEmpty()) return;
                mon.setMaxvalue(Integer.parseInt(year.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR) ?
                        Calendar.getInstance().get(Calendar.MONTH) + 1 : 12f);
                day.setMaxvalue(Integer.parseInt(year.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR)
                        && Integer.parseInt(mon.getText().toString()) == Calendar.getInstance().get(Calendar.MONTH) + 1 ?
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) : 31f);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }


    private boolean updateDB() {
        if (MainApp.superuser) return true;

        db = MainApp.appInfo.getDbHelper();
        long updcount = 0;
        try {
            updcount = db.updatesChildColumn(TableContracts.ChildTable.COLUMN_SIM, child.sIMtoString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, R.string.upd_db + e.getMessage());
            Toast.makeText(this, R.string.upd_db + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (updcount > 0) return true;
        else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void btnContinue(View view) {
        if (!formValidation()) return;
        if (updateDB()) {
            setResult(RESULT_OK);
            finish();
        } else Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
    }


    public void btnEnd(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }


    private boolean formValidation() {
        int im05 = 0; // single variable used for all IM05 variables
        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        //if (IMdates is valid date(not, 97, 66, 88, 44)){
        if (MainApp.child.getIm02().equals("1")) {
            // Only set once
            // IM04 - DOB
            String dobDate = MainApp.child.getIm04yy()
                    + "-" + MainApp.child.getIm04mm()
                    + "-" + MainApp.child.getIm04dd();

            // IM0501
            if (!bi.checkim0501.isChecked()) {
                String im0501date = MainApp.child.getIm0501yy()
                        + "-" + MainApp.child.getIm0501mm()
                        + "-" + MainApp.child.getIm0501dd();
                if (!validateDatesBCG(dobDate, im0501date)) {
                    displayInvalidDateDialog(bi.checkim0501, R.string.im0501bcg, bi.trIm0501);
                    return Validator.emptyCustomTextBox(this, bi.im0501yy, "Incorrect Date.");
                } else {
                    bi.checkim0501.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0501
                im05 = Integer.parseInt(MainApp.child.getIm0501dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0501dd, "Incorrect value for Day.");
                }
            }

            // IM0502
            if (!bi.checkim0502.isChecked()) {

                String im0502date = MainApp.child.getIm0502yy()
                        + "-" + MainApp.child.getIm0502mm()
                        + "-" + MainApp.child.getIm0502dd();
                if (!validateDatesBCG(dobDate, im0502date)) {
                    displayInvalidDateDialog(bi.checkim0502, R.string.im0502opv0, bi.trIm0502);
                    return Validator.emptyCustomTextBox(this, bi.im0502yy, "Incorrect Date.");
                } else {
                    bi.checkim0502.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0502
                im05 = Integer.parseInt(MainApp.child.getIm0502dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0502dd, "Incorrect value for Day.");
                }
            }


            /*firstVaccine*/
            String firstVaccine = MainApp.child.getIm0501yy()
                    + "-" + MainApp.child.getIm0501mm()
                    + "-" + MainApp.child.getIm0501dd();
            if (!bi.checkim0503.isChecked()) {

                // IM0503
                String im0503date = MainApp.child.getIm0503yy()
                        + "-" + MainApp.child.getIm0503mm()
                        + "-" + MainApp.child.getIm0503dd();
                if (!validateDates(firstVaccine, im0503date)) {
                    displayInvalidDateDialog(bi.checkim0503, R.string.im0503opv1, bi.trIm0503);
                    return Validator.emptyCustomTextBox(this, bi.im0503yy, "Incorrect Date.");
                } else {
                    bi.checkim0503.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0503
                im05 = Integer.parseInt(MainApp.child.getIm0503dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0503dd, "Incorrect value for Day.");
                }

            }
            if (!bi.checkim0504.isChecked()) {

                // IM0504
                String im0504date = MainApp.child.getIm0504yy()
                        + "-" + MainApp.child.getIm0504mm()
                        + "-" + MainApp.child.getIm0504dd();
                if (!validateDates(firstVaccine, im0504date)) {
                    displayInvalidDateDialog(bi.checkim0504, R.string.im0504penta1, bi.trIm0504);
                    return Validator.emptyCustomTextBox(this, bi.im0504yy, "Incorrect Date.");
                } else {
                    bi.checkim0504.setVisibility(View.VISIBLE);
                }
                // validate default values; initialize im105 for im0504
                im05 = Integer.parseInt(MainApp.child.getIm0501dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0501dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0505.isChecked()) {
                // IM0505
                String im0505date = MainApp.child.getIm0505yy()
                        + "-" + MainApp.child.getIm0505mm()
                        + "-" + MainApp.child.getIm0505dd();
                if (!validateDates(firstVaccine, im0505date)) {
                    displayInvalidDateDialog(bi.checkim0505, R.string.im0505pcv1, bi.trIm0505);
                    return Validator.emptyCustomTextBox(this, bi.im0505yy, "Incorrect Date.");
                } else {
                    bi.checkim0505.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0505
                im05 = Integer.parseInt(MainApp.child.getIm0505dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0505dd, "Incorrect value for Day.");
                }

            }
            if (!bi.checkim0506.isChecked()) {

                // IM0506
                String im0506date = MainApp.child.getIm0506yy()
                        + "-" + MainApp.child.getIm0506mm()
                        + "-" + MainApp.child.getIm0506dd();
                if (!validateDates(firstVaccine, im0506date)) {
                    displayInvalidDateDialog(bi.checkim0506, R.string.im0506rv1, bi.trIm0506);
                    return Validator.emptyCustomTextBox(this, bi.im0506yy, "Incorrect Date.");
                } else {
                    bi.checkim0506.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0506
                im05 = Integer.parseInt(MainApp.child.getIm0506dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0506dd, "Incorrect value for Day.");
                }
            }
            /*secondVaccine*/
            String secondVaccine = MainApp.child.getIm0506yy()
                    + "-" + MainApp.child.getIm0506mm()
                    + "-" + MainApp.child.getIm0506dd();


            if (!bi.checkim0507.isChecked()) {
                // IM0507
                String im0507date = MainApp.child.getIm0507yy()
                        + "-" + MainApp.child.getIm0507mm()
                        + "-" + MainApp.child.getIm0507dd();
                if (!validateDates(secondVaccine, im0507date)) {
                    displayInvalidDateDialog(bi.checkim0507, R.string.im0507opv2, bi.trIm0507);
                    return Validator.emptyCustomTextBox(this, bi.im0507yy, "Incorrect Date.");
                } else {
                    bi.checkim0507.setVisibility(View.VISIBLE);
                }
                // validate default values; initialize im105 for im0507
                im05 = Integer.parseInt(MainApp.child.getIm0507dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0507dd, "Incorrect value for Day.");
                }

            }
            if (!bi.checkim0508.isChecked()) {
                // IM0508
                String im0508date = MainApp.child.getIm0508yy()
                        + "-" + MainApp.child.getIm0508mm()
                        + "-" + MainApp.child.getIm0508dd();
                if (!validateDates(secondVaccine, im0508date)) {
                    displayInvalidDateDialog(bi.checkim0508, R.string.im0508penta2, bi.trIm0508);
                    return Validator.emptyCustomTextBox(this, bi.im0508yy, "Incorrect Date.");
                } else {
                    bi.checkim0508.setVisibility(View.VISIBLE);
                }
                // validate default values; initialize im105 for im0508
                im05 = Integer.parseInt(MainApp.child.getIm0508dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0508dd, "Incorrect value for Day.");
                }

            }
            if (!bi.checkim0509.isChecked()) {
                // IM0509
                String im0509date = MainApp.child.getIm0509yy()
                        + "-" + MainApp.child.getIm0509mm()
                        + "-" + MainApp.child.getIm0509dd();
                if (!validateDates(secondVaccine, im0509date)) {
                    displayInvalidDateDialog(bi.checkim0509, R.string.im0509pcv2, bi.trIm0509);
                    return Validator.emptyCustomTextBox(this, bi.im0509yy, "Incorrect Date.");
                } else {
                    bi.checkim0509.setVisibility(View.VISIBLE);
                }
                // validate default values; initialize im105 for im0509
                im05 = Integer.parseInt(MainApp.child.getIm0509dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0509dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0510.isChecked()) {
                // IM510
                String im0510date = MainApp.child.getIm0510yy()
                        + "-" + MainApp.child.getIm0510mm()
                        + "-" + MainApp.child.getIm0510dd();
                if (!validateDates(secondVaccine, im0510date)) {
                    displayInvalidDateDialog(bi.checkim0510, R.string.im0510rv2, bi.trIm0510);
                    return Validator.emptyCustomTextBox(this, bi.im0510yy, "Incorrect Date.");
                } else {
                    bi.checkim0510.setVisibility(View.VISIBLE);
                }
                // validate default values; initialize im105 for im0510
                im05 = Integer.parseInt(MainApp.child.getIm0510dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0510dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0510a.isChecked()) {
                // IM510a
                String im0510adate = MainApp.child.getIm0510ayy()
                        + "-" + MainApp.child.getIm0510amm()
                        + "-" + MainApp.child.getIm0510add();
                if (!validateDates(secondVaccine, im0510adate)) {
                    displayInvalidDateDialog(bi.checkim0510a, R.string.im0510ahp, bi.trIm0510a);
                    return Validator.emptyCustomTextBox(this, bi.im0510ayy, "Incorrect Date.");
                } else {
                    bi.checkim0510a.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0510a
                im05 = Integer.parseInt(MainApp.child.getIm0510add());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0510add, "Incorrect value for Day.");
                }
            }


            /*thirdVaccine*/
            String thirdVaccine = MainApp.child.getIm0510yy()
                    + "-" + MainApp.child.getIm0510mm()
                    + "-" + MainApp.child.getIm0510dd();
            if (!bi.checkim0511.isChecked()) {
                // IM05011
                String im0511date = MainApp.child.getIm0511yy()
                        + "-" + MainApp.child.getIm0511mm()
                        + "-" + MainApp.child.getIm0511dd();
                if (!validateDates(thirdVaccine, im0511date)) {
                    displayInvalidDateDialog(bi.checkim0511, R.string.im0511opv3, bi.trIm0511);
                    return Validator.emptyCustomTextBox(this, bi.im0511yy, "Incorrect Date.");
                } else {
                    bi.checkim0511.setVisibility(View.VISIBLE);
                }


                // validate default values; initialize im105 for im0511
                im05 = Integer.parseInt(MainApp.child.getIm0511dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0511dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0512.isChecked()) {
                // IM0512
                String im0512date = MainApp.child.getIm0512yy()
                        + "-" + MainApp.child.getIm0512mm()
                        + "-" + MainApp.child.getIm0512dd();
                if (!validateDates(thirdVaccine, im0512date)) {
                    displayInvalidDateDialog(bi.checkim0512, R.string.im0512penta3, bi.trIm0512);
                    return Validator.emptyCustomTextBox(this, bi.im0512yy, "Incorrect Date.");
                } else {
                    bi.checkim0512.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0512
                im05 = Integer.parseInt(MainApp.child.getIm0512dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0512dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0513.isChecked()) {
                // IM0513
                String im0513date = MainApp.child.getIm0513yy()
                        + "-" + MainApp.child.getIm0513mm()
                        + "-" + MainApp.child.getIm0513dd();
                if (!validateDates(thirdVaccine, im0513date)) {
                    displayInvalidDateDialog(bi.checkim0513, R.string.im0513pcv3, bi.trIm0513);
                    return Validator.emptyCustomTextBox(this, bi.im0513yy, "Incorrect Date.");
                } else {
                    bi.checkim0513.setVisibility(View.VISIBLE);
                }

                // validate default values; initialize im105 for im0501
                im05 = Integer.parseInt(MainApp.child.getIm0513dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0513dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0514.isChecked()) {
                // IM0514
                String im0514date = MainApp.child.getIm0514yy()
                        + "-" + MainApp.child.getIm0514mm()
                        + "-" + MainApp.child.getIm0514dd();
                if (!validateDates(thirdVaccine, im0514date)) {
                    displayInvalidDateDialog(bi.checkim0514, R.string.im0514ipv, bi.trIm0514);
                    return Validator.emptyCustomTextBox(this, bi.im0514yy, "Incorrect Date.");
                } else {
                    bi.checkim0514.setVisibility(View.VISIBLE);
                }
                // validate default values; initialize im105 for im0514
                im05 = Integer.parseInt(MainApp.child.getIm0514dd());
                if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                    return Validator.emptyCustomTextBox(this, bi.im0514dd, "Incorrect value for Day.");
                }
            }
            if (!bi.checkim0504.isChecked()) {
                /*forthVaccine*/
                String forthVaccine = MainApp.child.getIm0514yy()
                        + "-" + MainApp.child.getIm0514mm()
                        + "-" + MainApp.child.getIm0514dd();
                if (!bi.checkim0515.isChecked()) {

                    // IM0515
                    String im0515date = MainApp.child.getIm0515yy()
                            + "-" + MainApp.child.getIm0515mm()
                            + "-" + MainApp.child.getIm0515dd();
                    if (!validateDates(forthVaccine, im0515date)) {
                        displayInvalidDateDialog(bi.checkim0515, R.string.im0515measles1, bi.trIm0515);
                        return Validator.emptyCustomTextBox(this, bi.im0515yy, "Incorrect Date.");
                    } else {
                        bi.checkim0515.setVisibility(View.VISIBLE);
                    }
                    // validate default values; initialize im105 for im0515
                    im05 = Integer.parseInt(MainApp.child.getIm0515dd());
                    if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                        return Validator.emptyCustomTextBox(this, bi.im0515dd, "Incorrect value for Day.");
                    }
                }
                if (!bi.checkim0517.isChecked()) {
                    // IM0517
                    String im0517date = MainApp.child.getIm0517yy()
                            + "-" + MainApp.child.getIm0517mm()
                            + "-" + MainApp.child.getIm0517dd();
                    if (!validateDates(forthVaccine, im0517date)) {
                        displayInvalidDateDialog(bi.checkim0517, R.string.im0517typh, bi.trIm0517);
                        return Validator.emptyCustomTextBox(this, bi.im0517yy, "Incorrect Date.");
                    } else {
                        bi.checkim0517.setVisibility(View.VISIBLE);
                    }

                    // validate default values; initialize im105 for im0517
                    im05 = Integer.parseInt(MainApp.child.getIm0517dd());
                    if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                        return Validator.emptyCustomTextBox(this, bi.im0517dd, "Incorrect value for Day.");
                    }

                }
                /*fifthVaccine*/
                String fifthVaccine = MainApp.child.getIm0515yy()
                        + "-" + MainApp.child.getIm0515mm()
                        + "-" + MainApp.child.getIm0515dd();

                if (!bi.checkim0516.isChecked()) {
                    // IM0516
                    String im0516date = MainApp.child.getIm0516yy()
                            + "-" + MainApp.child.getIm0516mm()
                            + "-" + MainApp.child.getIm0516dd();
                    if (!validateDates(fifthVaccine, im0516date)) {
                        displayInvalidDateDialog(bi.checkim0516, R.string.im0516measles2, bi.trIm0516);
                        return Validator.emptyCustomTextBox(this, bi.im0516yy, "Incorrect Date.");
                    } else {
                        bi.checkim0516.setVisibility(View.VISIBLE);
                    }
                    // validate default values; initialize im105 for im0516
                    im05 = Integer.parseInt(MainApp.child.getIm0516dd());
                    if (im05 > 31 && (im05 != 44 && im05 != 66 && im05 != 88 && im05 != 97)) {
                        return Validator.emptyCustomTextBox(this, bi.im0516dd, "Incorrect value for Day.");
                    }
                }
            }

        }
        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }


    /*IMMUNIZATION DATES DIFFERENCE*/
    private boolean validateDatesBCG(String baseDate, String forwardDate) {
        if (baseDate.length() < 8 || forwardDate.length() < 8)
            return true;
        try {
            Calendar baseCal = Calendar.getInstance();
            Calendar forwardCal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            baseCal.setTime(sdf.parse(baseDate));// all done
            forwardCal.setTime(sdf.parse(forwardDate));// all done

            //return baseCal.compareTo(forwardCal) > -1 ; // >= basedate
            return forwardCal.getTimeInMillis() >= baseCal.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "ParseException(setDateRanges()): " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private boolean validateDates(String baseDate, String forwardDate) {
        if (baseDate.length() < 8 || forwardDate.length() < 8)
            return true;

        try {
            Calendar baseCal = Calendar.getInstance();
            Calendar forwardCal = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            baseCal.setTime(sdf.parse(baseDate));// all done
            forwardCal.setTime(sdf.parse(forwardDate));// all done

            // return baseCal.compareTo(forwardCal) > 0 ; // > basedate
            return forwardCal.getTimeInMillis() > baseCal.getTimeInMillis();


        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "ParseException(setDateRanges()): " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void takePhoto(View view) {

        Intent intent = new Intent(this, TakePhoto.class);

        intent.putExtra("picID", MainApp.form.getEbCode() + "_" + MainApp.form.getHhid() + "_" + MainApp.child.getSno() + "_");
        intent.putExtra("childName", MainApp.child.getEc14());

/*
        intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + "1" + "_");
        intent.putExtra("childName", "Hassan");
*/

        if (view.getId() == R.id.frontPhoto) {
            intent.putExtra("picView", "front".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front
        } else {
            intent.putExtra("picView", "back".toUpperCase());
            startActivityForResult(intent, 2); // Activity is started with requestCode 2 = Back
        }
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, requestCode + "_" + resultCode, Toast.LENGTH_SHORT).show();

            String fileName = data.getStringExtra("FileName");

            // Check if the requestCode 1 = Front : 2 = Back -- resultCode 1 = Success : 2 = Failure
            // Results received with requestCode 1 = Front

            if (requestCode == 1 && resultCode == RESULT_OK) {
                Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();

                bi.frontFileName.setText(fileName);
                bi.frontPhoto.setEnabled(false);


            } else if (requestCode == 1 && resultCode != RESULT_CANCELED) {
                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();

                //TODO: Implement functionality below when photo was not taken
                // ...
                bi.frontFileName.setText("Photo not taken.");

            }

            // Results received with requestCode 2 = Back
            if (requestCode == 2 && resultCode == RESULT_OK) {
                Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();

                bi.backFileName.setText(fileName);
                bi.backPhoto.setEnabled(false);
            } else if (requestCode == 2 && resultCode != RESULT_CANCELED) {

                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();

                //TODO: Implement functionality below when photo was not taken
                // ...
                bi.backFileName.setText("Photo not taken.");

            }
        }
    }


    private void displayInvalidDateDialog(CheckBox checkBox, int vaccLabel, TableRow tableRow) {
        new AlertDialog.Builder(this)
                .setTitle("Invalid Date: " + getResources().getString(vaccLabel))
                .setMessage("Please recheck date on vaccination card.")

                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
               /*         for (int i = 0; i < tableRow.getChildCount(); i++) {
                            View child = tableRow.getChildAt(i);
                            if(child instanceof EditText)
                            child.setEnabled(false);
                        }*/
                        //checkBox.setChecked(false);
                        checkBox.setVisibility(View.VISIBLE);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.no, null)
                .setIcon(R.drawable.ic_alert_24)
                .show();

    }
}