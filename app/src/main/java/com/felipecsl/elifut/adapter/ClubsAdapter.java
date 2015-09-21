package com.felipecsl.elifut.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felipecsl.elifut.R;
import com.felipecsl.elifut.models.Club;
import com.google.common.base.Preconditions;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public final class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ViewHolder> implements
    StickyRecyclerHeadersAdapter<ClubsAdapter.ViewHolder> {
  private final List<Club> clubs;
  private final Club selectedClub;

  public ClubsAdapter(List<Club> clubs, Club selectedClub) {
    this.selectedClub = selectedClub;
    this.clubs = Preconditions.checkNotNull(clubs);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(parent);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bind(position, clubs.get(position));
  }

  @Override public long getHeaderId(int position) {
    return 0;
  }

  @Override public ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
    return new HeaderViewHolder(parent);
  }

  @Override public void onBindHeaderViewHolder(ViewHolder holder, int position) {
    ((HeaderViewHolder) holder).bind();
  }

  @Override public int getItemCount() {
    return clubs.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.layout) ViewGroup layout;
    @Bind(R.id.txt_position) TextView position;
    @Bind(R.id.txt_club_name) TextView clubName;
    @Bind(R.id.txt_points) TextView points;
    @Bind(R.id.txt_wins) TextView wins;
    @Bind(R.id.txt_draws) TextView draws;
    @Bind(R.id.txt_losses) TextView losses;
    @Bind(R.id.txt_goals_difference) TextView goalsDifference;
    @BindColor(android.R.color.transparent) int transparent;
    @BindColor(R.color.club_table_header_bg) int headerBg;
    @BindColor(R.color.club_table_header_text_color) int headerTextColor;

    ViewHolder(ViewGroup parent) {
      super(LayoutInflater.from(parent.getContext()).inflate(R.layout.club_item, parent, false));
      ButterKnife.bind(this, itemView);
    }

    void bind(int pos, Club club) {
      position.setText(String.valueOf(pos + 1));
      clubName.setText(club.abbrev_name());
      clubName.setTypeface(null, selectedClub.equals(club) ? Typeface.BOLD : Typeface.NORMAL);
    }
  }

  private class HeaderViewHolder extends ViewHolder {
    HeaderViewHolder(ViewGroup parent) {
      super(parent);
    }

    void bind() {
      layout.setBackgroundColor(headerBg);
      clubName.setText("Team");
      clubName.setTextColor(headerTextColor);
      clubName.setBackgroundColor(transparent);
      points.setText("P");
      points.setTextColor(headerTextColor);
      points.setBackgroundColor(transparent);
      wins.setText("W");
      wins.setTextColor(headerTextColor);
      wins.setBackgroundColor(transparent);
      draws.setText("D");
      draws.setTextColor(headerTextColor);
      draws.setBackgroundColor(transparent);
      losses.setText("L");
      losses.setTextColor(headerTextColor);
      losses.setBackgroundColor(transparent);
      goalsDifference.setText("G");
      goalsDifference.setTextColor(headerTextColor);
      goalsDifference.setBackgroundColor(transparent);
    }
  }
}
