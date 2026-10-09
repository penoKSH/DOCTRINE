package com.sxs.doctrine;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import rikka.shizuku.Shizuku;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {
 private static final int REQUEST=45; private WebView webView;
 private final Shizuku.OnRequestPermissionResultListener listener=(c,r)->{if(c==REQUEST)runOnUiThread(()->webView.reload());};
 @Override public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  webView=findViewById(R.id.webView);WebSettings s=webView.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);
  webView.addJavascriptInterface(new Bridge(),"Doctrine");webView.loadUrl("file:///android_asset/index.html");Shizuku.addRequestPermissionResultListener(listener);}
 private boolean auth(){try{return Shizuku.pingBinder()&&Shizuku.checkSelfPermission()==android.content.pm.PackageManager.PERMISSION_GRANTED;}catch(Exception e){return false;}}
 private String exec(String[] c){try{Process p=Shizuku.newProcess(c,null,null);int n=p.waitFor();return n==0?"OK":"FAILED["+n+"]";}catch(Exception e){return "ERROR:"+e.getClass().getSimpleName();}}
 public class Bridge{
  @JavascriptInterface public String getStatus(){try{if(!Shizuku.pingBinder())return "Shizuku service not running";if(!auth()){Shizuku.requestPermission(REQUEST);return "Shizuku permission required";}return "Shizuku authorized";}catch(Exception e){return "Shizuku unavailable";}}
  @JavascriptInterface public String applyAll(String json){try{
   if(!Shizuku.pingBinder())return "FAILED: Shizuku service not running";
   if(!auth()){Shizuku.requestPermission(REQUEST);return "FAILED: Shizuku permission required";}
   JSONArray a=new JSONArray(json);int ok=0,fail=0;
   for(int i=0;i<a.length();i++){JSONObject o=a.getJSONObject(i);String k=o.getString("key"),v=o.getString("value");
    for(String ns:new String[]{"system","global","secure"}){if(exec(new String[]{"settings","put",ns,k,v}).equals("OK"))ok++;else fail++;}
   }
   return "APPLY SUCCESS • "+ok+" commands • "+fail+" failed";
  }catch(Exception e){return "ERROR:"+e.getClass().getSimpleName();}}
 }
 @Override protected void onDestroy(){Shizuku.removeRequestPermissionResultListener(listener);if(webView!=null)webView.destroy();super.onDestroy();}
}