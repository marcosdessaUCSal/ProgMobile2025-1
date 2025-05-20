package com.paginas;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.paginas.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;
    ViewPager2 pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Para expor a Toolbar na activity
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Para anexar o PagerAdapter ao ViewPager
        pagerAdapter = new PagerAdapter(this);
        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        // Para anexar o ViewPager ao TabLayout
        tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            // Define as abas do tablayout (incluindo seus rótulos)
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("ARNOLD");
                        break;
                    case 1:
                        tab.setText("STALLONE");
                        break;
                    case 2:
                        tab.setText("BTTF");
                        break;
                }
            }
        }).attach();
    }
}