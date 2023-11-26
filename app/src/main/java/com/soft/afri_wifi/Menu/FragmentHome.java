package com.soft.afri_wifi.Menu;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.soft.afri_wifi.Article.data.ArticleResponse;
import com.soft.afri_wifi.R;
import com.soft.afri_wifi.Ticket.data.TicketAdapter;
import com.soft.afri_wifi.Ticket.data.TicketRepository;
import com.soft.afri_wifi.Ticket.data.TicketResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TicketRepository ticketRepository;
    List<TicketResponse> list_local_ticket;
    TicketAdapter ticketAdapter;
    View root;
    RecyclerView recyclerViewTicket;
    ProgressBar progressBarLoadTicket;
    CardView cardViewEmpty;

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

        root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewTicket = root.findViewById(R.id.recycleForfait);
        progressBarLoadTicket = root.findViewById(R.id.progress_load_forfait);
        //refreshListeAchat = root.findViewById(R.id.achat_swipe_torefresh);
        cardViewEmpty = root.findViewById(R.id.card_empty);

        recyclerViewTicket.setHasFixedSize(true);
        recyclerViewTicket.setLayoutManager(new LinearLayoutManager(getContext()));

        ticketRepository = TicketRepository.getInstance();
        ticketAdapter = new TicketAdapter(getContext());


            LoadListeTicket(progressBarLoadTicket, recyclerViewTicket);



        return root;
    }

    public void LoadListeTicket(ProgressBar loadTicket, RecyclerView listeViewTicket)
    {
        Call<List<TicketResponse>> call_liste_fournisseur = ticketRepository.ticketConnexion().getListeTicket();
        loadTicket.setVisibility(View.VISIBLE);
        call_liste_fournisseur.enqueue(new Callback<List<TicketResponse>>() {
            @Override
            public void onResponse(Call<List<TicketResponse>> call, Response<List<TicketResponse>> response) {
                if (response.isSuccessful())
                {
                    loadTicket.setVisibility(View.GONE);
                    double _sortie_totale = 0;
                    double _vente_totale = 0;
                    list_local_ticket = new ArrayList<>();
                    for (int a = 0; a < response.body().size(); a++)
                    {
                        TicketResponse liste_ticket =
                                new TicketResponse (
                                        response.body().get(a).getDesignationTicket(),
                                        response.body().get(a).getPrixTicket(),
                                        response.body().get(a).getValiditeTicket(),
                                        response.body().get(a).getCatArticle()
                                );


                        list_local_ticket.add(liste_ticket);
                    }
                    ticketAdapter.setList(list_local_ticket);
                    listeViewTicket.setAdapter(ticketAdapter);

//                    if (list_local_ticket.isEmpty())
//                    {
//                        progressBarLoadTicket.setVisibility(View.INVISIBLE);
//                        cardViewEmpty.setVisibility(View.VISIBLE);
//
//                    }else
//                    {
//                        cardViewEmpty.setVisibility(View.GONE);
//                    }

                }
            }

            @Override
            public void onFailure(Call<List<TicketResponse>> call, Throwable t) {
                loadTicket.setVisibility(View.GONE);
            }
        });
    }

}