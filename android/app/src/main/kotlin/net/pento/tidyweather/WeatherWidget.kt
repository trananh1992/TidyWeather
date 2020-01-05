package net.pento.tidyweather

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.RemoteViews
import net.steamcrafted.materialiconlib.MaterialDrawableBuilder

/**
 * Implementation of App Widget functionality.
 */
class WeatherWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val prefs = context.getSharedPreferences( "net.pento.tidyweather.prefs", 0 )

    val current = prefs.getString( "current", "-" )
    val min = prefs.getString( "min", "" )
    val max = prefs.getString( "max", "" )
    val code = prefs.getString( "code", "" )

    val icon = MaterialDrawableBuilder
            .with( context )
            .setIcon( weatherIcon( code ) )
            .setColor( Color.WHITE )
            .build()

    // Construct the RemoteViews object
    val views = RemoteViews( context.packageName, R.layout.weather_widget )

    val intent = Intent( context, MainActivity::class.java )

    views.setOnClickPendingIntent( R.id.current_temp, PendingIntent.getActivity( context, 1, intent, 0 ) )
    views.setOnClickPendingIntent( R.id.temp_range, PendingIntent.getActivity( context, 1, intent, 0 ) )
    views.setOnClickPendingIntent( R.id.weather_icon, PendingIntent.getActivity( context, 1, intent, 0 ) )

    views.setTextViewText( R.id.current_temp, current )
    views.setTextViewText( R.id.temp_range, "$min-$max" )

    views.setImageViewBitmap( R.id.weather_icon, drawableToBitmap( icon ) )

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

internal fun weatherIcon( iconCode: String ): MaterialDrawableBuilder.IconValue? {
    val iconCodes = HashMap<String, MaterialDrawableBuilder.IconValue>()

  iconCodes[ "chance-shower-cloud" ] = MaterialDrawableBuilder.IconValue.WEATHER_RAINY
  iconCodes[ "chance-shower-fine" ] = MaterialDrawableBuilder.IconValue.WEATHER_PARTLYCLOUDY
  iconCodes[ "chance-snow-cloud" ] = MaterialDrawableBuilder.IconValue.WEATHER_SNOWY
  iconCodes[ "chance-snow-fine" ] = MaterialDrawableBuilder.IconValue.WEATHER_SNOWY
  iconCodes[ "chance-thunderstorm-cloud" ] = MaterialDrawableBuilder.IconValue.WEATHER_LIGHTNING
  iconCodes[ "chance-thunderstorm-fine" ] = MaterialDrawableBuilder.IconValue.WEATHER_LIGHTNING
  iconCodes[ "chance-thunderstorm-showers" ] = MaterialDrawableBuilder.IconValue.WEATHER_LIGHTNING_RAINY
  iconCodes[ "cloudy" ] = MaterialDrawableBuilder.IconValue.WEATHER_CLOUDY
  iconCodes[ "drizzle" ] = MaterialDrawableBuilder.IconValue.WEATHER_RAINY
  iconCodes[ "dust" ] = MaterialDrawableBuilder.IconValue.WEATHER_FOG
  iconCodes[ "few-showers" ] = MaterialDrawableBuilder.IconValue.WEATHER_RAINY
  iconCodes[ "fine" ] = MaterialDrawableBuilder.IconValue.WEATHER_SUNNY
  iconCodes[ "fog" ] = MaterialDrawableBuilder.IconValue.WEATHER_FOG
  iconCodes[ "frost" ] = MaterialDrawableBuilder.IconValue.SNOWFLAKE
  iconCodes[ "hail" ] = MaterialDrawableBuilder.IconValue.WEATHER_HAIL
  iconCodes[ "heavy-showers-rain" ] = MaterialDrawableBuilder.IconValue.WEATHER_POURING
  iconCodes[ "heavt-snow" ] = MaterialDrawableBuilder.IconValue.WEATHER_SNOWY
  iconCodes[ "high-cloud" ] = MaterialDrawableBuilder.IconValue.WEATHER_PARTLYCLOUDY
  iconCodes[ "light-snow" ] = MaterialDrawableBuilder.IconValue.WEATHER_SNOWY
  iconCodes[ "mostly-cloudy" ] = MaterialDrawableBuilder.IconValue.WEATHER_CLOUDY
  iconCodes[ "mostly-fine" ] = MaterialDrawableBuilder.IconValue.WEATHER_PARTLYCLOUDY
  iconCodes[ "overcast" ] = MaterialDrawableBuilder.IconValue.WEATHER_CLOUDY
  iconCodes[ "partly-cloudy" ] = MaterialDrawableBuilder.IconValue.WEATHER_PARTLYCLOUDY
  iconCodes[ "shower-or-two" ] = MaterialDrawableBuilder.IconValue.WEATHER_RAINY
  iconCodes[ "showers-rain" ] = MaterialDrawableBuilder.IconValue.WEATHER_RAINY
  iconCodes[ "snow" ] = MaterialDrawableBuilder.IconValue.WEATHER_SNOWY
  iconCodes[ "snow-and-rain" ] = MaterialDrawableBuilder.IconValue.WEATHER_SNOWY_RAINY
  iconCodes[ "thunderstorm" ] = MaterialDrawableBuilder.IconValue.WEATHER_LIGHTNING
  iconCodes[ "wind" ] = MaterialDrawableBuilder.IconValue.WEATHER_WINDY

  if ( iconCodes.containsKey( iconCode ) ) {
    return iconCodes[ iconCode ]
  }

  return MaterialDrawableBuilder.IconValue.TEXTURE
}

internal fun drawableToBitmap( drawable: Drawable ): Bitmap {
    var bitmap: Bitmap

    if ( drawable is BitmapDrawable ) {
        var bitmapDrawable: BitmapDrawable = drawable
        if ( bitmapDrawable.bitmap != null ) {
            return bitmapDrawable.bitmap
        }
    }

    if ( drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0 ) {
        bitmap = Bitmap.createBitmap( 1, 1, Bitmap.Config.ARGB_8888 )
    } else {
        bitmap = Bitmap.createBitmap( drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888 )
    }

    var canvas = Canvas( bitmap )
    drawable.setBounds( 0, 0, canvas.width, canvas.height )
    drawable.draw( canvas )

    return bitmap
}