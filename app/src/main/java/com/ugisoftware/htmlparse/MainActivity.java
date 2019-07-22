package com.ugisoftware.htmlparse;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ugisoftware.htmlparse.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener {
    private NavigationView nav_view;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ArrayList<String > firmalarArrayList = new ArrayList<>();
    private ListView lv;
    public ArrayList<String> liste= new ArrayList();
    public ArrayList linkliste= new ArrayList();
    private ArrayAdapter<String> adapter;
    private static String URL="http://www.tubitak.gov.tr/";
    public ArrayList<Integer> sayac=new ArrayList<>(  );//{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private ProgressDialog progressDialog;
    int[] Images={R.drawable.tubitaklogo,R.drawable.tubitaklogo,R.drawable.tubitaklogo,R.drawable.tubitaklogo,R.drawable.tubitaklogo,R.drawable.tubitaklogo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.List);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s =  lv.getItemAtPosition( position ).toString();


                for(int i=0;i<liste.size();i++)
                {
                    if(liste.get( i )==s)
                        {
                            //Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse( linkliste.get(i ).toString() ) );
                            //startActivity( browserIntent );
                            Intent browserIntent = new Intent( MainActivity.this,browser.class );
                            browserIntent.putExtra( "sayfa",linkliste.get( i ).toString() );
                            startActivity( browserIntent );
                        }




            }

            }
        });


        SharedPreferences sayacim=getSharedPreferences( "Veritabani",MODE_PRIVATE );
        /*Save
        SharedPreferences.Editor editor=sayacim.edit();
        editor.putInt( "tubitak1",yenideger );
        editor.commit();*/
        sayac.add( sayacim.getInt( "tubitak1" ,0) );
        sayac.add( sayacim.getInt( "tubitak2" ,0) );
        sayac.add( sayacim.getInt( "tubitak3" ,0) );
        sayac.add( sayacim.getInt( "tubitak4" ,0) );
        sayac.add( sayacim.getInt( "tubitak5" ,0) );
        sayac.add( sayacim.getInt( "tubitak6" ,0) );
        sayac.add( sayacim.getInt( "kosgeb" ,0) );
        sayac.add( sayacim.getInt( "istkk" ,0) );
        sayac.add( sayacim.getInt( "tka" ,0) );
        sayac.add( sayacim.getInt( "gmka" ,0) );
        sayac.add( sayacim.getInt( "ankara" ,0) );
        sayac.add( sayacim.getInt( "bursa" ,0) );
        sayac.add( sayacim.getInt( "dmka" ,0) );
        sayac.add( sayacim.getInt( "izka" ,0) );
        sayac.add( sayacim.getInt( "ahiler" ,0) );
        sayac.add( sayacim.getInt( "dicle" ,0) );
        sayac.add( sayacim.getInt( "geka" ,0) );
        sayac.add( sayacim.getInt( "karacadag" ,0) );
        sayac.add( sayacim.getInt( "ipekyolu" ,0) );
        sayac.add( sayacim.getInt( "zafer" ,0) );
        sayac.add( sayacim.getInt( "Doguanadolu" ,0) );
        sayac.add( sayacim.getInt( "mevlana" ,0) );
        sayac.add( sayacim.getInt( "BatıAkdeniz" ,0) );
        sayac.add( sayacim.getInt( "serhat" ,0) );
        sayac.add( sayacim.getInt( "KuzeyDoguAnd" ,0) );
        sayac.add( sayacim.getInt( "dogukar" ,0) );
        sayac.add( sayacim.getInt( "cukurova" ,0) );
        sayac.add( sayacim.getInt( "daka" ,0) );
        sayac.add( sayacim.getInt( "bkka" ,0) );
        sayac.add( sayacim.getInt( "kaka" ,0) );
        sayac.add( sayacim.getInt( "okka" ,0) );
        sayac.add( sayacim.getInt( "avrupa" ,0) );
        sayac.add( sayacim.getInt( "fırat" ,0) );
        sayac.add( sayacim.getInt( "oaka" ,0) );



        firmalarArrayList.add("Tubitak Akademik Destekler");
        firmalarArrayList.add("Tubitak Sanayi Destekleri");
        firmalarArrayList.add("Tubitak Kamu Destekleri");
        firmalarArrayList.add("Tubitak Girisimcilik Destekleri");
        firmalarArrayList.add("Tubitak Bilimsel Etkinlik Destekleri");
        firmalarArrayList.add("Tubitak Bilim ve Toplum Destekleri");
        firmalarArrayList.add("Kosgeb");
        firmalarArrayList.add("Avrupa Birliği");
        firmalarArrayList.add("İstanbul Kalkınma Ajansı");
        firmalarArrayList.add("Trakya Kalkınma Ajansı");
        firmalarArrayList.add("Güney Marmara Kalkınma Ajansı");
        firmalarArrayList.add("İzmir Kalkınma Ajansı");
        firmalarArrayList.add("Güney Ege Kalkınma Ajansı");
        firmalarArrayList.add("Zafer Kalkınma Ajansı");
        firmalarArrayList.add("Bursa-Eskişehir-Bilecik Kalkınma Ajansı");
        firmalarArrayList.add("Doğu Marmara Kalkınma Ajansı");
        firmalarArrayList.add("Ankara Kalkınma Ajansı");
        firmalarArrayList.add("Mevlana Kalkınma Ajansı");
        firmalarArrayList.add("Batı Karadeniz Kalkınma Ajansı");
        firmalarArrayList.add("Çukurova Kalkınma Ajansı");
        firmalarArrayList.add("Doğu Akdeniz Kalkınma Ajansı");
        firmalarArrayList.add("Ahiler Kalkınma Ajansı");
        firmalarArrayList.add("Orta Anadolu Kalkınma Ajansı");
        firmalarArrayList.add("Kuzey Anadolu Kalkınma Ajansı");
        firmalarArrayList.add("Kuzeydoğu Anadolu Kalkınma Ajansı");
        firmalarArrayList.add("Orta Karadeniz Kalkınma Ajansı");
        firmalarArrayList.add("Doğu Karadeniz Kalkınma Ajansı");
        firmalarArrayList.add("Serhat Kalkınma Ajansı");
        firmalarArrayList.add("Fırat Kalkınma Ajansı");
        firmalarArrayList.add("Doğu Anadolu Kalkınma Ajansı");
        firmalarArrayList.add("İpekyolu Kalkınma Ajansı");
        firmalarArrayList.add("Karacadağ Kalkınma Ajansı");
        firmalarArrayList.add("Dicle Kalkınma Ajansı");




        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                ,drawer,toolbar,0,0);
        //toggle ı drawer listenera ekledik
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View baslik = nav_view.inflateHeaderView(R.layout.nav_baslik);
        nav_view.setItemIconTintList(null);
        nav_view.setNavigationItemSelectedListener(MainActivity.this);
        nav_view.inflateMenu(R.menu.nav_menu);


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,liste);
     new VeriGetirTubitak().execute(  );
        //new VeriGetirTubitak().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Filtrelemek ve aramak için oluşturduğumuz menümüzün ana sayfa da görünmesini sağladuk

        getMenuInflater().inflate(R.menu.arama,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(MainActivity.this);//bu sınıfa bağladık
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ilanlarArama(newText);
        return false;
    }

    public void ilanlarArama(final String ilanId) {

       liste.clear();
       linkliste.clear();

        for (String s : firmalarArrayList){
            if(s.contains(ilanId)) {
                liste.add( s );

            }


        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a =  lv.getItemAtPosition( position ).toString();
                SharedPreferences sayacim=getSharedPreferences( "Veritabani",MODE_PRIVATE );
                for (int i = 0; i < liste.size(); i++) {
                    if (a.equals("Tubitak Akademik Destekler")) {
                        new VeriGetirTubitak().execute();
                        sayac.set( 0,sayac.get( 0 )+1 );

                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tubitak1",sayac.get( 0 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tubitak1" ,0);
                        Log.d("Akademik Destekler", String.valueOf( asd ) );


                    }
                    if (a.equals("Tubitak Sanayi Destekleri")) {
                        new VeriGetirTubitak1().execute();
                        sayac.set( 1,sayac.get( 1 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tubitak2",sayac.get( 1 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tubitak2" ,0);
                        Log.d("Sanayi Destekleri", String.valueOf( asd ) );

                    }
                    if (a.equals("Tubitak Kamu Destekleri")) {
                        new VeriGetirTubitak2().execute();
                        sayac.set( 2,sayac.get( 2 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tubitak3",sayac.get( 2 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tubitak3" ,0);
                        Log.d("Kamu Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Tubitak Girisimcilik Destekleri")) {
                        new VeriGetirTubitak3().execute();
                        sayac.set( 3,sayac.get( 3 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tubitak4",sayac.get( 3 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tubitak4" ,0);
                        Log.d("girisim Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Tubitak Bilimsel Etkinlik Destekleri")) {
                        new VeriGetirTubitak4().execute();
                        sayac.set( 4,sayac.get( 4 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tubitak5",sayac.get( 4 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tubitak5" ,0);
                        Log.d("bilim Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Tubitak Bilim ve Toplum Destekleri")) {
                        new VeriGetirTubitak5().execute();
                        sayac.set( 5,sayac.get( 5 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tubitak6",sayac.get( 5 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tubitak6" ,0);
                        Log.d("toplum Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Kosgeb")) {
                        new VeriGetirKosgeb().execute();
                        sayac.set( 6,sayac.get( 6 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "kosgeb",sayac.get( 6 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "kosgeb" ,0);
                        Log.d("kosgeb Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("İstanbul Kalkınma Ajansı")) {
                        new VeriGetirIstkalk().execute();
                        sayac.set( 7,sayac.get( 7 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "istka",sayac.get( 7 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "istka" ,0);
                        Log.d("istka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Trakya Kalkınma Ajansı")) {
                        new VeriGetirTrakya().execute();
                        sayac.set( 8,sayac.get( 8 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "tka",sayac.get( 8 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "tka" ,0);
                        Log.d("tka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Güney Marmara Kalkınma Ajansı")) {
                        new VeriGetirGMarmara().execute();
                        sayac.set( 9,sayac.get( 9 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "gmka",sayac.get( 9 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "gmka" ,0);
                        Log.d("gmka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Ankara Kalkınma Ajansı")) {
                        new VeriGetirAnkara().execute();
                        sayac.set( 10,sayac.get( 10 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "ankara",sayac.get( 10 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "ankara" ,0);
                        Log.d("ankara Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Bursa-Eskişehir-Bilecik Kalkınma Ajansı")) {
                        new VeriGetirBursa().execute();
                        sayac.set( 11,sayac.get( 11 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "bursa",sayac.get( 11 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "bursa" ,0);
                        Log.d("bursa Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Doğu Marmara Kalkınma Ajansı")) {
                        new VeriGetirDMarmara().execute();
                        sayac.set( 12,sayac.get( 12 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "dmka",sayac.get( 12 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "dmka" ,0);
                        Log.d("dmka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("İzmir Kalkınma Ajansı")) {
                        new VeriGetirIzkalk().execute();
                        sayac.set( 13,sayac.get( 13 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "izka",sayac.get( 13 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "izka" ,0);
                        Log.d("izka Destekleri", String.valueOf( asd ) );
                    }

                    if (a.equals("Ahiler Kalkınma Ajansı")) {
                        new VeriGetirAhika().execute();
                        sayac.set( 14,sayac.get( 14 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "ahiler",sayac.get( 14 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "ahiler" ,0);
                        Log.d("ahiler Destekleri", String.valueOf( asd ) );
                    }

                    if (a.equals("Dicle Kalkınma Ajansı")) {
                        new VeriGetirDicle().execute();
                        sayac.set( 15,sayac.get( 15 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "dicle",sayac.get( 15 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "dicle" ,0);
                        Log.d("dicle Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Güney Ege Kalkınma Ajansı")) {
                        new VeriGetirGEKA().execute();
                        sayac.set( 16,sayac.get( 16 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "geka",sayac.get(16 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "geka" ,0);
                        Log.d("geka Destekleri", String.valueOf( asd ) );
                    }

                    if (a.equals("Karacadağ Kalkınma Ajansı")) {
                        new VeriGetirKaracadag().execute();
                        sayac.set( 17,sayac.get( 17 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "karacadag",sayac.get( 17 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "karacadag" ,0);
                        Log.d("karacadag Destekleri", String.valueOf( asd ) );
                    }

                    if (a.equals("İpekyolu Kalkınma Ajansı")) {
                         new VeriGetirIpekyolu().execute();
                        sayac.set( 18,sayac.get( 18 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "ipekyolu",sayac.get( 18 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "ipekyolu" ,0);
                        Log.d("ipekyolu Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Zafer Kalkınma Ajansı")) {
                        new VeriGetirZafer().execute();
                        sayac.set( 19,sayac.get( 19 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "zafer",sayac.get( 19 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "zafer" ,0);
                        Log.d("zafer Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Doğu Anadolu Kalkınma Ajansı")) {
                        new VeriGetirDanadolu().execute();
                        sayac.set( 20,sayac.get( 20 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "Doguanadolu",sayac.get( 20 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "Doguanadolu" ,0);
                        Log.d("Doguanadolu Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Mevlana Kalkınma Ajansı")) {
                        new VeriGetirMevlana().execute();
                        sayac.set( 21,sayac.get( 21 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "mevlana",sayac.get( 21 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "mevlana" ,0);
                        Log.d("mevlana Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Batı Akdeniz Kalkınma Ajansı")) {
                        new VeriGetirBatıAkd().execute();
                        sayac.set( 22,sayac.get( 22 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "BatıAkdeniz",sayac.get( 22 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "BatıAkdeniz" ,0);
                        Log.d("BatıAkdeniz Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Serhat Kalkınma Ajansı")) {
                        new VeriGetirSerhat().execute();
                        sayac.set( 23,sayac.get( 23 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "serhat",sayac.get( 23 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "serhat" ,0);
                        Log.d("serhat Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Kuzeydoğu Anadolu Kalkınma Ajansı")) {
                        new VeriGetirKDAnadolu().execute();
                        sayac.set( 24,sayac.get( 24 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "KuzeyDoguAnd",sayac.get( 24 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "KuzeyDoguAnd" ,0);
                        Log.d("KuzeyDoguAnd Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Doğu Karadeniz Kalkınma Ajansı")) {
                        new VeriGetirDKaradeniz().execute();
                        sayac.set( 25,sayac.get( 25 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "dogukar",sayac.get( 25 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "dogukar" ,0);
                        Log.d("dogukar Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Çukurova Kalkınma Ajansı")) {
                        new VeriGetirCukurova().execute();
                        sayac.set( 26,sayac.get( 26 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "cukurova",sayac.get( 26 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "cukurova" ,0);
                        Log.d("cukurova Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Doğu Akdeniz Kalkınma Ajansı")) {
                        new VeriGetirDoguAkdeniz().execute();
                        sayac.set( 27,sayac.get( 27 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "daka",sayac.get( 27 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "daka" ,0);
                        Log.d("daka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Batı Karadeniz Kalkınma Ajansı")) {
                        new VeriGetirBatıKaradeniz().execute();
                        sayac.set( 28,sayac.get( 28 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "bkka",sayac.get( 28 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "bkka" ,0);
                        Log.d("bkka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Kuzey Anadolu Kalkınma Ajansı")) {
                        new VeriGetirKuzeyAnadolu().execute();
                        sayac.set( 29,sayac.get(29 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "kaka",sayac.get( 29 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "kaka" ,0);
                        Log.d("kaka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Orta Karadeniz Kalkınma Ajansı")) {
                        new VeriGetirOrtaKaradeniz().execute();
                        sayac.set( 30,sayac.get( 30 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "okka",sayac.get( 30 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "okka" ,0);
                        Log.d("okka Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Avrupa Birliği Destek Programı")) {
                        new VeriGetirAB().execute();
                        sayac.set( 31,sayac.get( 31 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "avrupa",sayac.get( 31 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "avrupa" ,0);
                        Log.d("avrupa Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Fırat Kalkınma Ajansı")) {
                        new VeriGetirFırat().execute();
                        sayac.set( 32,sayac.get( 32 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "fırat",sayac.get( 32 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "fırat" ,0);
                        Log.d("fırat Destekleri", String.valueOf( asd ) );
                    }
                    if (a.equals("Orta Anadolu Kalkınma Ajansı")) {
                        new VeriGetirOrtaAnadolu().execute();
                        sayac.set( 33,sayac.get( 33 )+1 );
                        SharedPreferences.Editor editor=sayacim.edit();
                        editor.putInt( "oaka",sayac.get( 33 ) );
                        editor.commit();
                        int asd=sayacim.getInt( "oaka" ,0);
                        Log.d("oaka Destekleri", String.valueOf( asd ) );
                    }
                   else {
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String s =  lv.getItemAtPosition( position ).toString();

                                   for (int i = 0; i < liste.size(); i++) {
                                       if (liste.get( i ) == s) {
                                           //Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse( linkliste.get(i ).toString() ) );
                                           //startActivity( browserIntent );
                                           Intent browserIntent = new Intent( MainActivity.this, browser.class );
                                           browserIntent.putExtra( "sayfa", linkliste.get( i ).toString() );
                                           startActivity( browserIntent );
                                       }
                                   }


                            }
                        });


                    }

                }
            }
        });


        adapter.notifyDataSetChanged();
    }

    private class VeriGetirKosgeb extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {

                Document doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6312/girisimcilik-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                        Element duyuruDiv = adDiv.select("div.details").first();

                        liste.add(duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                 doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6313/arge-teknolojik-uretim-ve-yerlilestirme-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add(duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6314/isletme-gelistirme-buyume-ve-uluslararasilasma-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add(duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6315/kobi-finansman-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add(duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6316/laboratuvar-hizmetleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add(duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6343/isgemtekmer-programi/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add(duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirAB extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://www.istka.org.tr/destekler/destek-turleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("a.link")){
                    Element duyuruDiv = adDiv.select("a.link").first();

                    //liste.add( linkA.absUrl("href")) ;

                }
                linkliste.add( "http://www.ab.gov.tr/ab-programlari-hibeleri_101.html") ;
                liste.add("Güncel Destekler ");
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirFırat extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://www.istka.org.tr/destekler/destek-turleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("a.link")){
                    Element duyuruDiv = adDiv.select("a.link").first();

                    //liste.add( linkA.absUrl("href")) ;

                }
                linkliste.add( "https://fka.gov.tr/mali-destekler-detayi-1463574099720/") ;
                liste.add("Güncel Destekler ");
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirIstkalk extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://www.istka.org.tr/destekler/destek-turleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("a.link")){
                        Element duyuruDiv = adDiv.select("a.link").first();

                        //liste.add( linkA.absUrl("href")) ;

                }
                linkliste.add( "https://www.istka.org.tr/destekler/acik-destek-programlari/") ;
                liste.add("Fizibilite Destek Programı ");
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirIzkalk extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://www.izka.org.tr/destekler.html").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.feature__body.boxed.boxed--border.boxed--lg")){
                    Element duyuruDiv = adDiv.select("div.feature__body.boxed.boxed--border.boxed--lg").first();
                    liste.add(duyuruDiv.text());
                    Element linkA = adDiv.select("a").first();
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTubitak extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/akademik/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }





            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTubitak1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {



                Document doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/sanayi/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTubitak2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {

                Document doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/kamu/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTubitak3 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {

                Document doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/girisimcilik/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }


            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTubitak4 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {

               Document doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/bilimsel-etkinlik/etkinlik-duzenleme-destekleri").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }


            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTubitak5 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {

               Document doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/bilim-ve-toplum/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirTrakya extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("https://www.trakyaka.org.tr").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.duyuru-item")){

                        Element duyuruDiv = adDiv.select("div.duyuru-item").first();
                    Element aaDiv = duyuruDiv.select("div.t").first();
                    Element abDiv = aaDiv.select("h5").first();
                        Element linkA = abDiv.select("a").first();

                }
                liste.add(" Proje Teklif Çağrısı");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "https://www.trakyaka.org.tr/tr/38750/2019-Yili-Proje-Teklif-Cagrisi") ;


            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirGMarmara extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("https://www.gmka.gov.tr/ajans-destek-turleri").timeout(30*1000).get();
                for (Element adDiv : doc.select("div#sidebar1")){

                    Element duyuruDiv = adDiv.select("div#sidebar1").first();
                    Element LinkDiv = adDiv.select("a").first();

                }
                liste.add("Açık Destek Programları ");

                linkliste.add("https://www.gmka.gov.tr/acik-destek-programlari") ;
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirGEKA extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://geka.gov.tr/3073/guncel-destekler").timeout(30*1000).get();
                for (Element adDiv : doc.select("ul.page-list")){

                    Element duyuruDiv = adDiv.select("li").first();
                    Element LinkDiv = duyuruDiv.select("a").first();
                    liste.add(LinkDiv.text());

                    linkliste.add(LinkDiv.absUrl("href")) ;
                }
                liste.add( "Guncel Destekler" );
                linkliste.add( "http://geka.gov.tr/3073/guncel-destekler" );
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirBursa extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("https://www.bebka.org.tr/destekler/acik-destek-programlari-48").timeout(30*1000).get();
                for (Element adDiv : doc.select("span")){


                    Element LinkDiv = adDiv.select("a").first();
                    liste.add(LinkDiv.text());

                    linkliste.add(LinkDiv.absUrl("href")) ;
                    break;
                }
                liste.add( " Guncel Destekler" );
                linkliste.add( "https://www.bebka.org.tr/destekler/acik-destek-programlari-48" );
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirZafer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                Document doc= Jsoup.connect("http://www.zafer.gov.tr/tr-tr/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.col-md-3.col-sm-3")){

                   Element duyuruDiv=adDiv.select("div.col-md-3.col-sm-3").first();
                    Element LinkDiv = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());

                    linkliste.add(LinkDiv.absUrl("href")) ;

                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirAnkara extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.ankaraka.org.tr/tr/2019-yili-teknik-destek-programi_4470.html").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.page-title")){
                        Element duyuruDiv = adDiv.select("div.page-title").first();
                        Element linkA = adDiv.select("a").first();

                }
                liste.add(" Teknik Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "https://www.ankaraka.org.tr/tr/2019-yili-teknik-destek-programi_4470.html") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirAhika extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.ahika.gov.tr/destekler/ahika-acik-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.col1")){
                    Element duyuruDiv = adDiv.select("div.col1").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirMevlana extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.mevka.org.tr/Pages.asp?Dil=0&kid=536").timeout(30*1000).get();
                for (Element adDiv : doc.select("table.one")){
                    //a
                    Element duyuruDiv = adDiv.select("table.one").first();
                    Element textDiv = duyuruDiv.select("td").first();
                    Element linkA = textDiv.select("a").first();
                    liste.add(textDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;
                }
                liste.add("Güncel Destekler");
                linkliste.add( "http://www.mevka.org.tr/Pages.asp?Dil=0&kid=536" );
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirOrtaKaradeniz extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("https://www.oka.org.tr/okaIcerik.aspx?Id=41").timeout(30*1000).get();
                for (Element adDiv : doc.select("a")){
                    Element duyuruDiv = adDiv.select("a").first();
                    Element linkA = duyuruDiv.select("span").first();

                }
                liste.add( "Güncel Destekler" );
                linkliste.add( "https://www.oka.org.tr/okaIcerik.aspx?Id=41" );
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirDMarmara extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.marka.org.tr/sayfa/351/657/2019-yili-destekleri").timeout(30*1000).get();
                for (Element adDiv : doc.select("div#divContent")){
                    Element duyuruDiv = adDiv.select("a").first();
                    Element linkA = duyuruDiv.select("span").first();
                    liste.add(linkA.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( duyuruDiv.absUrl("href")) ;
                }
                liste.add( "Güncel Destekeler" );
                linkliste.add( "http://www.marka.org.tr/sayfa/351/657/2019-yili-destekleri" );
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirDicle extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.ankaraka.org.tr/tr/2019-yili-teknik-destek-programi_4470.html").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.page-title")){
                    Element duyuruDiv = adDiv.select("div.page-title").first();
                    Element linkA = adDiv.select("a").first();

                }
                liste.add("Teknik Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.dika.org.tr/TR/Sayfa/2019_yili_teknik_destek_programi") ;
                liste.add("Fizibilite Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.dika.org.tr/TR/Sayfa/2019_yili_fizibilite_destegi_ilani") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirBatıAkd extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.baka.org.tr/mali-destek-programlari-S127.html").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.page-title")){
                    Element duyuruDiv = adDiv.select("div.page-title").first();
                    Element linkA = adDiv.select("a").first();

                }
                liste.add("Mali Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.baka.org.tr/mali-destek-programlari-S127.html") ;
                liste.add("Teknik Destekler  ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.baka.org.tr/teknik-destekler-S123.html") ;
                liste.add("Güncel Destekler  ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.baka.org.tr/ajans-destekleri-S122.html") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirKaracadag extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.ankaraka.org.tr/tr/2019-yili-teknik-destek-programi_4470.html").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.page-title")){
                    Element duyuruDiv = adDiv.select("div.page-title").first();
                    Element linkA = adDiv.select("a").first();

                }
                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "https://www.karacadag.gov.tr/destekler/") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirCukurova extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.cka.org.tr/main.aspx?id=419#").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.page-title")){
                    Element duyuruDiv = adDiv.select("div.page-title").first();
                    Element linkA = adDiv.select("a").first();

                }
                liste.add("Mali Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.cka.org.tr/main.aspx?id=419") ;
                liste.add("Teknik Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.cka.org.tr/main.aspx?id=421/") ;
                liste.add("Fizibilite Destekleri");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.cka.org.tr/main.aspx?id=420") ;
                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.cka.org.tr/main.aspx?id=419#/") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirBatıKaradeniz extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.cka.org.tr/main.aspx?id=419#").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.page-title")){
                    Element duyuruDiv = adDiv.select("div.page-title").first();
                    Element linkA = adDiv.select("a").first();

                }

                liste.add(" Teknik Destekler ");

                linkliste.add( "https://www.bakka.gov.tr/site/sayfa/187/2019-yili-teknik-destek-programi//") ;

                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "https://www.bakka.gov.tr/site/sayfa/182/acik-destek-programlari//") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirKuzeyAnadolu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("https://www.kuzka.gov.tr/destekler-detay.asp?DD=292&DesteklerDetay=acik-mali-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("li.AnaMenu")){
                    Element duyuruDiv = adDiv.select("li.AnaMenu").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());

                    linkliste.add( linkA.absUrl("href")) ;

                }
                liste.add("Güncel Destekler");

                linkliste.add("https://www.kuzka.gov.tr/destekler-detay.asp?DD=292&DesteklerDetay=acik-mali-destek-programlari") ;


            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirOrtaAnadolu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.oran.org.tr/tr/destekler/mali-destek-programi").timeout(30*1000).get();
                for (Element adDiv : doc.select("ul.solmenu")){
                    Element linkA=adDiv.select( "a" ).first();
                    liste.add(adDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;
                }


            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirDoguAkdeniz extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.dogaka.gov.tr/Destekler.asp?D=24&Destekler=teknik-destek-programi").timeout(30*1000).get();
                for (Element adDiv : doc.select("a.ListeMenuAlt")){
                    liste.add(adDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( adDiv.absUrl("href")) ;
                }

                doc= Jsoup.connect("http://www.dogaka.gov.tr/Destekler.asp?D=84&Destekler=fizibilite-destegi").timeout(30*1000).get();
                for (Element adDiv : doc.select("a.ListeMenuAlt")){
                    liste.add(adDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( adDiv.absUrl("href")) ;
                }
                doc= Jsoup.connect("http://www.dogaka.gov.tr/Destekler.asp?D=11&Destekler=mali-destek-programi").timeout(30*1000).get();
                for (Element adDiv : doc.select("a.ListeMenuAlt")){
                    liste.add(adDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( adDiv.absUrl("href")) ;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirIpekyolu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect("http://www.ika.org.tr/hibe.html").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.icerik")){
                    Element duyuruDiv = adDiv.select("div.icerik").first();
                    Element linkA = duyuruDiv.select("a").first();


                }
                liste.add(" Mali Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.ika.org.tr/2019-Yili-Mali-Destek-Programlari-icerik-349.html") ;
                liste.add("Fizibilite Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "https://www.ika.org.tr/2019-Yili-Fizibilite-Destegi-Programi-icerik-351.html") ;
                liste.add("Güncel Destekler");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "https://www.ika.org.tr/Fizibilite-Destegi-icerik-339.html") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirDanadolu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.daka.org.tr/icerikler/acikdestek").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.hkyazi")){
                    Element duyuruDi = adDiv.select("div.hkyazi").first();
                    Element duyuruDiv = duyuruDi.select("li").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add(duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;
                }
                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.daka.org.tr/icerikler/acikdestek") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirSerhat extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.daka.org.tr/icerikler/acikdestek").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.hkyazi")){
                    Element duyuruDi = adDiv.select("div.hkyazi").first();
                    Element duyuruDiv = duyuruDi.select("li").first();
                    Element linkA = adDiv.select("a").first();
                }
                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.serka.gov.tr/destekler/acik-destek-programlari") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirKDAnadolu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.daka.org.tr/icerikler/acikdestek").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.hkyazi")){
                    Element duyuruDi = adDiv.select("div.hkyazi").first();
                    Element duyuruDiv = duyuruDi.select("li").first();
                    Element linkA = adDiv.select("a").first();
                }
                liste.add("Mali Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://pyb.kudaka.org.tr/Home/tug2019") ;
                liste.add("Teknik Destek Programı ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://pyb.kudaka.org.tr/Home/teknikdestek2019") ;
                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://pyb.kudaka.org.tr") ;


            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }
    private class VeriGetirDKaradeniz extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
            linkliste.clear();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc= Jsoup.connect("http://www.daka.org.tr/icerikler/acikdestek").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.hkyazi")){
                    Element duyuruDi = adDiv.select("div.hkyazi").first();
                    Element duyuruDiv = duyuruDi.select("li").first();
                    Element linkA = adDiv.select("a").first();
                }
                liste.add("Güncel Destekler ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.doka.org.tr/destekler_Acik-TR.html") ;

            }
            catch (IOException e) {
                e.printStackTrace();
                liste.add("Connection Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter( adapter );
            progressDialog.dismiss();

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //menüdeki seçeneklerden birine tıklanıldığında ne açılacak onu belirtiyoruz

        int id = menuItem.getItemId();

        if (id == R.id.id1) {
            new VeriGetirTubitak().execute();
            toolbar.setTitle("Tübitak-Akademik Destekler");


        }
        if (id == R.id.id2) {
            new VeriGetirTubitak1().execute();
            toolbar.setTitle("Tübitak-Sanayi Destekleri");


        }
        if (id == R.id.id3) {
            new VeriGetirTubitak2().execute();
            toolbar.setTitle("Tübitak-Kamu Destekleri");


        }
        if (id == R.id.id4) {
            new VeriGetirTubitak3().execute();
            toolbar.setTitle("Tübitak-Girişimcilik Destekleri");


        }
        if (id == R.id.id5) {
            new VeriGetirTubitak4().execute();
            toolbar.setTitle("Tübitak-Bilimsel Etkinlik Destekleri");


        }
        if (id == R.id.id6) {
            new VeriGetirTubitak5().execute();
            toolbar.setTitle("Tübitak-Bilim ve Toplum Destekleri");

        }
        if (id == R.id.action_kosgeb) {
            new VeriGetirKosgeb().execute();
            toolbar.setTitle("Kosgeb");

        }
        if (id == R.id.action_ist) {
            new VeriGetirIstkalk().execute();
            toolbar.setTitle("İstanbul Kalkınma Ajansı");

        }
        if (id == R.id.action_trakya) {
            new VeriGetirTrakya().execute();
            toolbar.setTitle("Trakya Kalkınma Ajansı");

        }
        if (id == R.id.action_gmarmara) {
            new VeriGetirGMarmara().execute();
            toolbar.setTitle("Güney Marmara Kalkınma Ajansı");

        }
        if (id == R.id.action_ankara) {
            new VeriGetirAnkara().execute();
            toolbar.setTitle("Ankara Kalkınma Ajansı");

        }
        if (id == R.id.action_beb) {
            new VeriGetirBursa().execute();
            toolbar.setTitle("Bursa-Eskişehir-Bilecik Kalkınma Ajansı");

        }
        if (id == R.id.action_dmarmara) {
            new VeriGetirDMarmara().execute();
            toolbar.setTitle("Doğu Marmara Kalkınma Ajansı");

        }
        if (id == R.id.action_izmir) {
            new VeriGetirIzkalk().execute();
            toolbar.setTitle("İzmir Kalkınma Ajansı");

        }

        if (id == R.id.action_ahiler) {
            new VeriGetirAhika().execute();
            toolbar.setTitle("Ahiler Kalkınma Ajansı");

        }

        if (id == R.id.action_dicle) {
            new VeriGetirDicle().execute();
            toolbar.setTitle("Dicle Kalkınma Ajansı");

        }
        if (id == R.id.action_gege) {
            new VeriGetirGEKA().execute();
            toolbar.setTitle("Güney Ege Kalkınma Ajansı");

        }

        if (id == R.id.action_karacadag) {
            new VeriGetirKaracadag().execute();
            toolbar.setTitle("Karacadağ Kalkınma Ajansı");

        }

        if (id == R.id.action_ipekyolu) {
            new VeriGetirIpekyolu().execute();
            toolbar.setTitle("İpekyolu Kalkınma Ajansı");
        }
        if (id == R.id.action_zafer) {
            new VeriGetirZafer().execute();
          toolbar.setTitle("Zafer Kalkınma Ajansı");

        }
        if (id == R.id.action_danadolu) {
            new VeriGetirDanadolu().execute();
            toolbar.setTitle("Doğu Anadolu Kalkınma Ajansı");

        }
        if (id == R.id.action_mevlana) {
            new VeriGetirMevlana().execute();
            toolbar.setTitle("Mevlana Kalkınma Ajansı");

        }
        if (id == R.id.action_bakdeniz) {
            new VeriGetirBatıAkd().execute();
            toolbar.setTitle("Batı Akdeniz Kalkınma Ajansı");

        }
        if (id == R.id.action_serhat) {
            new VeriGetirSerhat().execute();
            toolbar.setTitle("Serhat Kalkınma Ajansı");

        }if (id == R.id.action_kdanadolu) {
            new VeriGetirKDAnadolu().execute();
            toolbar.setTitle("Kuzeydoğu Anadolu Kalkınma Ajansı");

        }if (id == R.id.action_dkaradeniz) {
            new VeriGetirDKaradeniz().execute();

            toolbar.setTitle("Doğu Karadeniz Kalkınma Ajansı");

        }
        if (id == R.id.action_cukurova) {
            new VeriGetirCukurova().execute();
            toolbar.setTitle("Çukurova Kalkınma Ajansı");


        }
        if (id == R.id.action_dakdeniz) {
            new VeriGetirDoguAkdeniz().execute();
            toolbar.setTitle("Doğu Akdeniz Kalkınma Ajansı");

        }
        if (id == R.id.action_bkaradeniz) {
            new VeriGetirBatıKaradeniz().execute();
            toolbar.setTitle("Batı Karadeniz Kalkınma Ajansı");

        }
        if (id == R.id.action_kanadolu) {
            new VeriGetirKuzeyAnadolu().execute();
            toolbar.setTitle("Kuzey Anadolu Kalkınma Ajansı");


        }
        if (id == R.id.action_okaradeniz) {
            new VeriGetirOrtaKaradeniz().execute();
            toolbar.setTitle("Orta Karadeniz Kalkınma Ajansı");


        }
        if (id == R.id.action_avrupa) {
            new VeriGetirAB().execute();
            toolbar.setTitle("Avrupa Birliği Destek Programı");


        }
        if (id == R.id.action_firat) {
            new VeriGetirFırat().execute();
            toolbar.setTitle("Fırat Kalkınma Ajansı");

        }if (id == R.id.action_oanadolu) {
            new VeriGetirOrtaAnadolu().execute();
            toolbar.setTitle("Orta Anadolu Kalkınma Ajansı");

        }
        //menüden seçim yaptıktan sonra nav viewin kapalı konuma geçmesini sağlar
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
