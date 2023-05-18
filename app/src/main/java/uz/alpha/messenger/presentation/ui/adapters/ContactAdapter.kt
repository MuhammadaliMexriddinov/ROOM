package uz.alpha.messenger.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.alpha.messenger.R
import uz.alpha.messenger.data.room.entity.ContactEntity
import uz.alpha.messenger.databinding.ItemContactBinding
import kotlin.math.abs

class ContactAdapter :
    ListAdapter<ContactEntity, ContactAdapter.ViewHolder>(itemReminderCallback) {

    private var itemDeleteListener: ((ContactEntity) -> Unit)? = null

    fun setOnDeleteClickListener(block: (ContactEntity) -> Unit) {
        itemDeleteListener = block
    }

    private var itemListener: ((ContactEntity) -> Unit)? = null

    fun setOnListener(block: (ContactEntity) -> Unit) {
        itemListener = block
    }

    private var itemEditListener: ((ContactEntity) -> Unit)? = null

    fun setOnEditListener(block: (ContactEntity) -> Unit) {
        itemEditListener = block
    }

    inner class ViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition))
            }

            binding.root.setOnLongClickListener {
                itemDeleteListener?.invoke(getItem(absoluteAdapterPosition))
                true
            }

            binding.btnEdit.setOnClickListener {
                itemEditListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
               getItemId(absoluteAdapterPosition).apply {
                   txtFullName.text=data.fullName
                   txtDate.text=data.date
               }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemContactBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private val itemReminderCallback = object : DiffUtil.ItemCallback<ContactEntity>() {
    override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean =
        oldItem.id == newItem.id && oldItem.fullName == newItem.fullName

}