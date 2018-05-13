package com.example.nesthabit.fragment;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.nesthabit.R;
import com.example.nesthabit.adapter.SystemSoundAdapter;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/24.
 */
public class ClockSoundSetFragment extends BaseFragment {

    @BindView(R.id.clock_sound_vibrate)
    Switch clockSoundVibrate;
    @BindView(R.id.clock_sound_select_item)
    RelativeLayout clockSoundSelectItem;
    @BindView(R.id.system_sound_recycler_view)
    RecyclerView systemSoundRecyclerView;

    Unbinder unbinder;
    private SystemSoundAdapter adapter;
    private SoundSetCallback soundSetCallback;

    private List<Sound> soundList;
    private Sound selectedSound;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_clock_sound;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
        }
        soundList = getSystemSound(getActivity());
        if (!soundList.isEmpty()) {
            soundSetCallback.onSoundSet(soundList.get(0));
        }
        initView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            soundSetCallback = (SoundSetCallback) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("铃声");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static List<Sound> getSystemSound(Context context) {
        RingtoneManager manager = new RingtoneManager(context);
        List<Sound> sounds = new ArrayList<>();
        manager.setType(RingtoneManager.TYPE_ALL);
        Cursor cursor = manager.getCursor();
        if (cursor.moveToFirst()) {
            do {
                Sound sound = new Sound(
                        cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX),
                        manager.getRingtoneUri(cursor.getPosition()));
                if (sound.getUri() != null) {
                    sounds.add(sound);
                }
            } while (cursor.moveToNext());
        }
        return sounds;
    }

    private void initView() {
        adapter = new SystemSoundAdapter(soundList);
        adapter.setItemOnClickListener((view, position) -> {
            selectedSound = soundList.get(position);
            soundSetCallback.onSoundSet(selectedSound);
            adapter.setCurrentSelectedSound(position);
            adapter.notifyDataSetChanged();
        });
        systemSoundRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        systemSoundRecyclerView.setAdapter(adapter);
        systemSoundRecyclerView.addItemDecoration(new DividerItemDecoration(
                Objects.requireNonNull(getActivity()),
                DividerItemDecoration.VERTICAL));
    }

    @OnClick(R.id.clock_sound_vibrate)
    public void onViewClicked() {
        if (clockSoundVibrate.isChecked()) {
            soundSetCallback.setIsVibrate(1);
        } else {
            soundSetCallback.setIsVibrate(0);
        }
    }

    public interface SoundSetCallback {
        void onSoundSet(Sound sound);
        void setIsVibrate(int isVibrate);
    }
}
