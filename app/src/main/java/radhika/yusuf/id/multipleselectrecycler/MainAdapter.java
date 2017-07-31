package radhika.yusuf.id.multipleselectrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ♥ by Radhika Yusuf on 31/07/17.
 */


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<MainData> mData;
    private List<Integer> selectedIndexs;
    private Context mContext;
    private MainActivity mainActivity;

    /**
     * false = normal , true = edit mod
     */
    private boolean mode = false;

    public MainAdapter(List<MainData> mData, MainActivity mainActivity) {
        this.mData = mData;
        this.selectedIndexs = new ArrayList<>();
        this.mainActivity = mainActivity;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());

        holder.tvTitle.setBackgroundColor(isAvailableInList(position) ?
                mContext.getResources().getColor(R.color.colorTitle)  :
                mContext.getResources().getColor(R.color.colorWhite)) ;

        holder.tvTitle.setTextColor(isAvailableInList(position) ?
                mContext.getResources().getColor(R.color.colorWhite)  :
                mContext.getResources().getColor(R.color.colorTitle)) ;


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mode){
                    Toast.makeText(mContext, R.string.action_toast, Toast.LENGTH_SHORT).show();
                }else{
                    if(isAvailableInList(position)){
                        selectedIndexs.remove(getIndex(position));
                        mainActivity.clearDeleteAction();
                        if (selectedIndexs.size() == 0){
                            mode = false;
                        }
                    }else{
                        selectedIndexs.add(position);
                        mainActivity.showDeleteAction();
                    }
                    MainAdapter.this.notifyDataSetChanged();
                }
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mode = !mode;
                selectedIndexs.add(position);
                mainActivity.showDeleteAction();
                MainAdapter.this.notifyDataSetChanged();
                return true;
            }
        });
    }

    private int getIndex(int position) {
        int result = 0;
        for (int i = 0; i < selectedIndexs.size(); i++) {
            if(position == selectedIndexs.get(i)){
                result = i;
                break;
            }
        }
        return result;
    }

    private boolean isAvailableInList(int position) {
        boolean result = false;
        for (int i = 0; i < selectedIndexs.size(); i++) {
            if(position == selectedIndexs.get(i)){
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public List<Integer> getSelectedIndexs() {
        return selectedIndexs;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.title_row_main);
        }
    }
}
