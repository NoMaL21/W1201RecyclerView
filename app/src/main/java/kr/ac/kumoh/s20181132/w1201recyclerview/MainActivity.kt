package kr.ac.kumoh.s20181132.w1201recyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20181132.w1201recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: ListViewModel by viewModels()
    //이걸 쓸려면  implementation 'androidx.activity:activity-ktx:1.6.1' 이걸 넣어줘야 한다.
    //이러면 lateinit 안해도 된다.
    private val songAdapter = SongAdapter()

    // @SuppressLint("NotifyDataSetChanged") // notifyDataSetChanged()워닝 내지 말라고 해놓은것
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.apply{ //apply로 앞에 있는것들 없애줄 수 있다.
            layoutManager = LinearLayoutManager(applicationContext) //this는 이걸로 바꿔줘야 한다.
            itemAnimator = DefaultItemAnimator() //애니메이터
            setHasFixedSize(true) //오류 줄이기용
            adapter = songAdapter //SongAdapter()를 songAdapter로 바꿔줌
        }

        model.getList().observe(this){
            songAdapter.notifyDataSetChanged() //워닝 내지 말라고 서프레스 해줄수도 있긴하다.
            //데이터가 하나만 추가가 되어도 전체가 리프레시가 되는 상황을 막기 위해서 워닝이 뜨는것.
            //일단은 놔둠
        }
        for (i in 1..3)
        {
            model.add("I really want to stay at your house")
        }
        model.add("New Future")
    }
    //SongAdapter 에서 Implement members 하고, 3개 다 shift로 클릭해서 만들기
    //이게 처음에 말한 3개의 맴버다.
    inner class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>(){ //타입은 vh라고 되어있다. Viewholder라는뜻
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //뷰홀더 구현
            val txSong: TextView = itemView.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            //인플레이션을 해줘야 한다. // parent를 넘겨줬기 때문에 그걸 사용함
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false) //바로 붙일지 말지 결정, 여기선 false임
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //holder.txSong.text = "New Future" //하드코딩한 이부분 바꾸기
            holder.txSong.text = model.getSong(position)

        }

        override fun getItemCount() = model.getSize() //30 //하드코딩된 30을 getSize로 바꾸기

    }
}

