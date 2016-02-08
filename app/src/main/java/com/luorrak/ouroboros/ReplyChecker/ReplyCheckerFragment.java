package com.luorrak.ouroboros.ReplyChecker;

import android.app.Fragment;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luorrak.ouroboros.R;
import com.luorrak.ouroboros.util.InfiniteDbHelper;

/**
 * Ouroboros - An 8chan browser
 * Copyright (C) 2015  Luorrak
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class ReplyCheckerFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_reply_checker, container, false);
        InfiniteDbHelper infiniteDbHelper = new InfiniteDbHelper(getActivity());

        Cursor userPostsCursor = infiniteDbHelper.getFlaggedUserPostsCursor();
        Log.d("Stuff", DatabaseUtils.dumpCurrentRowToString(userPostsCursor));
        if((userPostsCursor != null) && (userPostsCursor .getCount() > 0)){
            recyclerView = (RecyclerView) view.findViewById(R.id.reply_checker_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            ReplyCheckerAdapter replyCheckerAdapter = new ReplyCheckerAdapter(userPostsCursor);
            recyclerView.setAdapter(replyCheckerAdapter);
        }
        return view;
    }
}