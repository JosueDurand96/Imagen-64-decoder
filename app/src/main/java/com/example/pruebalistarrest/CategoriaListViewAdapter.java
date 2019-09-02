package com.example.pruebalistarrest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
public class CategoriaListViewAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<nthcPoligono> items;
    private Context mContext;
    private String urlServer = "http://www.capacitasoft.com/site/Administrador/";
    public CategoriaListViewAdapter(Activity context, List<nthcPoligono> items) {
        super();
        this.mContext = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        nthcPoligono item = items.get(position);

        if(convertView==null)
            convertView = inflater.inflate(R.layout.lista_categorias, null);

        ImageView imgThumbnail = (ImageView)convertView.findViewById(R.id.imageViewImagen);
//        TextView txtTitle = (TextView)convertView.findViewById(R.id.textViewNombre);
        // TextView txtSubtitle = (TextView)convertView.findViewById(R.id.textViewPrecio);


      //  Picasso.with(mContext).load(urlServer+"categoria/"+item.imagen).into(imgThumbnail);
     //   txtTitle.setText(item.getImagen1());
        //  txtSubtitle.setText("S/"+item.precio);
        String string = item.imagen3;
        byte[]  decoded =  android.util.Base64.decode(string, android.util.Base64.DEFAULT);
    //   byte[] decoded = Base64.getDecoder().decode(string);

    //    byte[] decoded = Base64.getDecoder().decode(string.getBytes(StandardCharsets.UTF_8));
        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        imgThumbnail.setImageBitmap(bitmap);

        return convertView;
    }
}
