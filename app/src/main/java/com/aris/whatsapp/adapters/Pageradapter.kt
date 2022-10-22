package com.aris.whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aris.whatsapp.fragments.chats
import com.aris.whatsapp.fragments.users


class Pageradapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
       when(position) {
           0 -> return users()
           1 -> return chats()
       }
           return null!!
       }

    override fun getPageTitle(position: Int): CharSequence? {


        when (position){
            0 -> return "Users"
            1 -> return "Chats"
        }

        return null
    }

    }
