package com.codingwithme.notesapp.util

import java.security.MessageDigest


class PasswordHash {
    companion object {
        fun hash(password: String): String {
            val md = MessageDigest.getInstance("SHA-512")
            md.reset()
            md.update(password.toByteArray())
            val mdArray = md.digest()
            val sb = StringBuilder(mdArray.size * 2)
            for (b in mdArray) {
                val v = b.toInt() and 0xff
                if (v < 16) sb.append('0')
                sb.append(Integer.toHexString(v))
            }

            return sb.toString();
        }
        fun verify(password: String, hashed: String): Boolean {
            return hash(password) == hashed;
        }
    }
}