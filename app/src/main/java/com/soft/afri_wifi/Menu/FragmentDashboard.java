package com.soft.afri_wifi.Menu;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.soft.afri_wifi.Compte.data.CompteRepository;
import com.soft.afri_wifi.Compte.data.CompteResponse;
import com.soft.afri_wifi.DataBase.DataFromAPI;
import com.soft.afri_wifi.R;
import com.soft.afri_wifi.Releve.AdapterReleve;
import com.soft.afri_wifi.Releve.TransactionCaisseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDashboard newInstance(String param1, String param2) {
        FragmentDashboard fragment = new FragmentDashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    View root;
    CompteRepository compteRepository;
    ArrayList<CompteResponse> list_local_releve;
    AdapterReleve adapterReleve;

    SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    String pref_code_depot, pref_compte_user, pref_compte_stock_user,nom_user, pref_mode_type,
            todayDate, prefix_operation;

    DataFromAPI dadataFromAPI;
    Calendar calendar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        TextView display_balance = root.findViewById(R.id.textView4);
        TextView display_account_name = root.findViewById(R.id.textViewNomUser);
        TextView clickAllTransactions = root.findViewById(R.id.textViewAllTransctions);
        ProgressBar progressBar = root.findViewById(R.id.progressBar);
        ProgressBar progressBarReleve = root.findViewById(R.id.progressBarReleve);
        RecyclerView recyclerViewReleve = root.findViewById(R.id.recycleReleve);

        recyclerViewReleve.setHasFixedSize(true);
        recyclerViewReleve.setLayoutManager(new LinearLayoutManager(getContext()));

        compteRepository = CompteRepository.getInstance();
        adapterReleve = new AdapterReleve(getContext());

        preferences = getActivity().getSharedPreferences("maPreference", MODE_PRIVATE);
        editor = preferences.edit();

        dadataFromAPI = new DataFromAPI(getContext());

        calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = format.format(calendar.getTime());

        pref_code_depot = preferences.getString("pref_depot_user","");
        pref_compte_user = preferences.getString("pref_compte_user","");
        nom_user = preferences.getString("pref_nom_user","");
        pref_compte_stock_user = preferences.getString("pref_compte_stock_user","");
        pref_mode_type = preferences.getString("pref_mode_type","");

        display_account_name.setText(nom_user);

        LoadSoldeCaisse(Integer.parseInt(pref_compte_user), progressBar, display_balance);

        LoadListeReleveCompte(progressBarReleve, recyclerViewReleve, Integer.parseInt(pref_compte_user));

        clickAllTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TransactionCaisseActivity.class));
            }
        });


        return root;
    }

    public void LoadSoldeCaisse(int numCompte, ProgressBar loadSolde, TextView displaySoldeCaisse)
    {
        Call<Double> call_solde_compte = compteRepository.compteConnexion().getSoldeCaisse(numCompte);
        loadSolde.setVisibility(View.VISIBLE);
        call_solde_compte.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful())
                {
                    loadSolde.setVisibility(View.GONE);
                    displaySoldeCaisse.setText(String.format("CDF  %s", response.body()));
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                loadSolde.setVisibility(View.GONE);
            }
        });
    }

    public void LoadListeReleveCompte(ProgressBar loadTransactions, RecyclerView listeReleve,
                                      int numCompte)
    {
        Call<List<CompteResponse>> call_liste_releve = compteRepository.compteConnexion().getTenLastOp(numCompte);
        loadTransactions.setVisibility(View.VISIBLE);
        call_liste_releve.enqueue(new Callback<List<CompteResponse>>() {
            @Override
            public void onResponse(Call<List<CompteResponse>> call, Response<List<CompteResponse>> response) {
                if (response.isSuccessful())
                {
                    loadTransactions.setVisibility(View.GONE);
                    double _sortie_totale = 0;
                    double _achat_total = 0;
                    list_local_releve = new ArrayList<>();
                    for (int a = 0; a < response.body().size(); a++)
                    {
                        CompteResponse liste_releve =
                                new CompteResponse (
                                        response.body().get(a).getLibelle(),
                                        response.body().get(a).getDateOperation(),
                                        response.body().get(a).getDebit(),
                                        response.body().get(a).getCredit(),
                                        response.body().get(a).getSolde()

                                );

                        _achat_total += response.body().get(a).getCredit();

//                        textView_solde_jour.setText(String.format("$%s", response.body().get(a).getSolde()));
//                        textView_solde_achat.setText(String.format("$%s", _achat_total));

                        list_local_releve.add(liste_releve);
                    }
                    adapterReleve.setList(list_local_releve);
                    listeReleve.setAdapter(adapterReleve);

                }
            }

            @Override
            public void onFailure(Call<List<CompteResponse>> call, Throwable t) {
                loadTransactions.setVisibility(View.GONE);
            }
        });
    }
}