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


            int counter=0;
            try {
                Document doc= Jsoup.connect(URL).timeout(30*1000).get();
                for (Element adDiv : doc.select("div.views-field.views-field-title")){
                   if(counter<6)
                   {
                    Element duyuruDiv = adDiv.select("div.views-field.views-field-title").first();
                    Element linkA = adDiv.select("a").first();
                    liste.add("Tübitak   : "+duyuruDiv.text());
                    //liste.add( linkA.absUrl("href")) ;
                    linkliste.add( linkA.absUrl("href")) ;
                   counter++;
                   }
                   else break;

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



        //menüden seçim yaptıktan sonra nav viewin kapalı konuma geçmesini sağlar

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }








}
