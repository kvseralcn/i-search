package com.example.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapp.databinding.SearchpagelistItemBinding
import com.example.movieapp.model.ContentResultModel

class SearchAdapter constructor(
    private val searchList: List<ContentResultModel>,
    private val onItemClick: (ContentResultModel) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.PageHolder>() {

    class PageHolder(val binding: SearchpagelistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PageHolder {
        val binding =
            SearchpagelistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PageHolder(binding)
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val contentResult = searchList[position]
        Glide.with(holder.itemView.context)
            .load(contentResult.artworkUrl100)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.searchpagelistItemImvCover)

        holder.binding.searchpagelistItemTvartistName.text = contentResult.artistName.toString()
        holder.binding.searchpagelistItemTvcollectionName.text =
            "${contentResult.trackName} - ${contentResult.collectionName}"
        //task.trackName + " " + task.collectionName.toString()

        holder.binding.playButton.setOnClickListener {
            Log.d("MediaPlayer", "URL: ${contentResult.previewUrl}") // URL'yi logla
            onItemClick(contentResult)
        }

    }

    override fun getItemCount() = searchList.size

    /* private fun playMusic(url: String?, context: Context) {
         if (url.isNullOrEmpty()) {
             Toast.makeText(context, "Müzik URL'si bulunamadı.", Toast.LENGTH_SHORT).show()
             return
         }

         val mediaPlayer = MediaPlayer()
         mediaPlayer.setAudioAttributes(
             AudioAttributes.Builder()
                 .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                 .setUsage(AudioAttributes.USAGE_MEDIA)
                 .build()
         )
         mediaPlayer.setDataSource(url)

         mediaPlayer.setOnPreparedListener { mp ->
             mp.start()
             Log.d("MediaPlayer", "Müzik başlatıldı.")
         }

         mediaPlayer.setOnErrorListener { mp, what, extra ->
             Log.e("MediaPlayer", "Hata kodu: $what, Ekstra: $extra")
             true // Hata işleme başarısız oldu, true dön
         }

         mediaPlayer.prepareAsync()
     }*/
}