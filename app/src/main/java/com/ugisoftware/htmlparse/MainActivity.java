package com.ugisoftware.htmlparse;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView nav_view;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ListView lv;
    public ArrayList liste= new ArrayList();
    public ArrayList linkliste= new ArrayList();

    private ArrayAdapter<String> adapter;
    private static String URL="http://www.tubitak.gov.tr/";
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

        new VeriGetirTubitak().execute();
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

                        liste.add("Kosgeb   : "+duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                 doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6313/arge-teknolojik-uretim-ve-yerlilestirme-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add("Kosgeb   : "+duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6314/isletme-gelistirme-buyume-ve-uluslararasilasma-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add("Kosgeb   : "+duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6315/kobi-finansman-destekleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add("Kosgeb   : "+duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6316/laboratuvar-hizmetleri/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add("Kosgeb   : "+duyuruDiv.text() );



                }
                for (Element adDiv : doc.select("a.dashboard-stat.dashboard-stat-v2.blue-chambray.shadow")){

                    linkliste.add( adDiv.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.kosgeb.gov.tr/site/tr/genel/destekler/6343/isgemtekmer-programi/").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.details")){

                    Element duyuruDiv = adDiv.select("div.details").first();

                    liste.add("Kosgeb   : "+duyuruDiv.text() );



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
                liste.add("İstKA   : 2019 Yılı Fizibilite Desteği Programı ");
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
                    liste.add("İzmirKA   : "+duyuruDiv.text());
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
                    liste.add("Tübitak  Akademik  Destekler  : "+duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }



                doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/sanayi/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add("Tübitak  Sanayi  Destekleri : "+duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/kamu/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add("Tübitak  Kamu  Destekleri : "+duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/girisimcilik/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add("Tübitak  Girişimcilik  Destekleri : "+duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/bilimsel-etkinlik/etkinlik-duzenleme-destekleri").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add("Tübitak  Bilimsel Etkinlik  Destekleri : "+duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;

                }
                doc= Jsoup.connect("http://www.tubitak.gov.tr/tr/destekler/bilim-ve-toplum/ulusal-destek-programlari").timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){

                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add("Tübitak  Bilim ve Toplum  Destekleri : "+duyuruDiv.text());
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
                liste.add("2019 YILI PROJE TEKLİF ÇAĞRISI İLANI ");
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
                liste.add("GMarmara   : Açık Destek Programları ");

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
                    liste.add("GEKA   : "+LinkDiv.text());

                    linkliste.add(LinkDiv.absUrl("href")) ;
                }
                liste.add( "GEKA : Guncel Destekler" );
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
                    liste.add("Bursa KA   : "+LinkDiv.text());

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
                    liste.add("Zafer KA   : "+duyuruDiv.text());

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
                liste.add("2019 YILI TEKNİK DESTEK PROGRAMI ");
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
                    liste.add("AhiKA   : "+duyuruDiv.text());
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
                    Element duyuruDiv = adDiv.select("table.one").first();
                    Element textDiv = duyuruDiv.select("td").first();
                    Element linkA = textDiv.select("a").first();
                    liste.add("Mevlana   : "+textDiv.text());
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
                    liste.add("Doğu Marmara   : "+linkA.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( duyuruDiv.absUrl("href")) ;
                }
                liste.add( "2019 Yılı Güncel Destekeler" );
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
                liste.add("2019 YILI TEKNİK DESTEK PROGRAMI ");
                //liste.add( linkA.absUrl("href")) ;
                linkliste.add( "http://www.dika.org.tr/TR/Sayfa/2019_yili_teknik_destek_programi") ;
                liste.add("2019 YILI FİZİBİLİTE DESTEK PROGRAMI ");
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
                    liste.add("İpekyolu   : "+duyuruDiv.text());
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




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //menüdeki seçeneklerden birine tıklanıldığında ne açılacak onu belirtiyoruz

        int id = menuItem.getItemId();

        if (id == R.id.action_tubitak) {
            new VeriGetirTubitak().execute();
            //Aa
        }
        if (id == R.id.action_kosgeb) {
            new VeriGetirKosgeb().execute();
            //Aa
        }
        if (id == R.id.action_ist) {
            new VeriGetirIstkalk().execute();
            //Aa
        }
        if (id == R.id.action_trakya) {
            new VeriGetirTrakya().execute();
            //Aa
        }
        if (id == R.id.action_gmarmara) {
            new VeriGetirGMarmara().execute();
            //Aa
        }
        if (id == R.id.action_ankara) {
            new VeriGetirAnkara().execute();
            //Aa
        }
        if (id == R.id.action_beb) {
            new VeriGetirBursa().execute();
            //Aa
        }
        if (id == R.id.action_dmarmara) {
            new VeriGetirDMarmara().execute();
            //Aa
        }
        if (id == R.id.action_izmir) {
            new VeriGetirIzkalk().execute();
            //Aa
        }

        if (id == R.id.action_ahiler) {
            new VeriGetirAhika().execute();
            //Aa
        }

        if (id == R.id.action_dicle) {
            new VeriGetirDicle().execute();
            //Aa
        }


        if (id == R.id.action_gege) {
            new VeriGetirGEKA().execute();
            //Aa
        }

        if (id == R.id.action_karacadag) {
            new VeriGetirKaracadag().execute();
            //Aa
        }


        if (id == R.id.action_ipekyolu) {
            new VeriGetirIpekyolu().execute();
        }
        if (id == R.id.action_zafer) {
            new VeriGetirZafer().execute();

            //Aa
        }
        if (id == R.id.action_mevlana) {
            new VeriGetirMevlana().execute();

            //Aa
        }

        //menüden seçim yaptıktan sonra nav viewin kapalı konuma geçmesini sağlar
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }








}
