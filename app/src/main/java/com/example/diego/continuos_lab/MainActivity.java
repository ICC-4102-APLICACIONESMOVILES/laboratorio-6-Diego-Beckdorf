package com.example.diego.continuos_lab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.diego.continuos_lab.database.AppDatabase;
import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.Answer;
import com.example.diego.continuos_lab.database_orm.AnswerSet;
import com.example.diego.continuos_lab.database_orm.Form;
import com.example.diego.continuos_lab.database_orm.Question;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NewFormFragment.NewFormListener,
        FormFragment.FormDeleter,
        AnswerFormFragment.FormResponseSaver {

    private static final String DATABASE_NAME = "forms_db";
    private AppDatabase appDatabase;
    private Location currentLocation;

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View fragContainer = findViewById(R.id.fragment_container);
        if (fragContainer != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            DefaultFragment firstFragment = new DefaultFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();


        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        this.navChangeFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void navChangeFragment(int fragmentId) {
        if (findViewById(R.id.fragment_container) != null) {

            if (fragmentId == R.id.nav_home) {
                DefaultFragment defaultFragment = new DefaultFragment();
                defaultFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, defaultFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            } else if (fragmentId == R.id.nav_new_form) {
                NewFormFragment newFormFragment = new NewFormFragment();
                newFormFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFormFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            } else if (fragmentId == R.id.nav_forms) {
                FormFragment formFragment = new FormFragment();
                formFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, formFragment, "MainFragmentTag");
                transaction.addToBackStack(null);
                transaction.commit();
                populateFormListFragment(formFragment);
            } else if (fragmentId == R.id.nav_summary) {
                SummaryFragment summaryFragment = new SummaryFragment();
                summaryFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, summaryFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }


    public void loadFormResponseFragment(long formId) {
        if (findViewById(R.id.fragment_container) != null) {
            AnswerFormFragment responseFragment = new AnswerFormFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("formId", formId);
            responseFragment.setArguments(getIntent().getExtras());
            responseFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, responseFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            renderQuestionsListView(responseFragment, formId);
        }
    }


    public AppDatabase getAppDatabase() {
        return appDatabase;
    }


    private void populateFormListFragment(final FormFragment fragment) {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                List<Form> forms = (List<Form>) msg.obj;
                fragment.populateTable(forms);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                DaoAccess dao = getAppDatabase().daoAccess();
                List<Form> forms = dao.getForms();
                Message msg = new Message();
                msg.obj = forms;
                handler.sendMessage(msg);
            }
        }).start();
    }


    private void renderQuestionsListView(final AnswerFormFragment fragment, final long formId) {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                List<Question> questions = (List<Question>) msg.obj;
                fragment.renderQuestions(questions);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                DaoAccess dao = getAppDatabase().daoAccess();
                List<Question> questions = dao.getFormQuestions(formId);
                Message msg = new Message();
                msg.obj = questions;
                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void newForm(final String name, final String date, final String category,
                        final String description, final List<Question> questionList) {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String toastMessage = (String) msg.obj;
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                MainActivity.this.navChangeFragment(R.id.nav_forms);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                DaoAccess dao = getAppDatabase().daoAccess();
                Form form = new Form(name, date, category, description);
                try {
                    long formId = dao.insertSingleForm(form);
                    Question question;
                    for (int i = 0; i < questionList.size(); i++) {
                        question = questionList.get(i);
                        question.setFormId(formId);
                        dao.insertSingleQuestion(question);
                    }
                    msg.obj = "Form created!";
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    msg.obj = "Could not create form.";
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    public void callFormDelete(final Form form) {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                boolean daoResponse = (boolean) msg.obj;
                String toastMessage;
                if (daoResponse) {
                    toastMessage = "Form deleted";
                } else {
                    toastMessage = "Failed to delete form";
                }
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                navChangeFragment(R.id.nav_forms);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                DaoAccess dao = getAppDatabase().daoAccess();
                Message msg = new Message();
                try {
                    dao.deleteForm(form);
                    msg.obj = true;
                } catch (Exception e) {
                    msg.obj = false;
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    public void saveResponse(final List<Answer> answerList) {
        //TODO: Get user ref for answerSets
        System.out.println("calling from  ainActivity");
        final FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            System.out.println("Prmisions denied");
            return;
        }
        fusedLocationProviderClient.getLastLocation().
                addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            currentLocation = location;
                        }
                    }
                });
        // TODO: set location to AnserSet object related with user
        for (int i = 0; i < answerList.size(); i++) {
            answerList.get(i).setLatitude(currentLocation.getLatitude());
            answerList.get(i).setLongitude(currentLocation.getLongitude());
        }

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                boolean daoResponse = (boolean) msg.obj;
                String toastMessage;
                if (daoResponse) {
                    toastMessage = "Response saved";
                } else {
                    toastMessage = "Failed to save response";
                }
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                MainActivity.this.navChangeFragment(R.id.nav_forms);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                DaoAccess dao = getAppDatabase().daoAccess();
                Message msg = new Message();
                try {
                    dao.insertAnswers(answerList);
                    msg.obj = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    msg.obj = false;
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
}
