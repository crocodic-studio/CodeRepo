<article class="markdown-body entry-content" itemprop="text"><h1><a href="#android-signature-pad" aria-hidden="true" class="anchor" id="user-content-android-signature-pad"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Signature Pad</h1>
<p><em>Lihat <code>SignatureActivity.java</code> untuk contoh penggunaan library.</em></p>
<ol>
<li>Tambahkan view <code>SignaturePad</code> ke dalam layout</li>
</ol>
<div class="highlight highlight-text-xml"><pre> &lt;<span class="pl-ent">com</span>.crocodic.signaturepad.SignaturePad
     <span class="pl-e">xmlns</span><span class="pl-e">:</span><span class="pl-e">android</span>=<span class="pl-s"><span class="pl-pds">"</span>http://schemas.android.com/apk/res/android<span class="pl-pds">"</span></span>
     <span class="pl-e">xmlns</span><span class="pl-e">:</span><span class="pl-e">app</span>=<span class="pl-s"><span class="pl-pds">"</span>http://schemas.android.com/apk/res-auto<span class="pl-pds">"</span></span>
     <span class="pl-e">android</span><span class="pl-e">:</span><span class="pl-e">id</span>=<span class="pl-s"><span class="pl-pds">"</span>@+id/signature_pad<span class="pl-pds">"</span></span>
     <span class="pl-e">android</span><span class="pl-e">:</span><span class="pl-e">layout_width</span>=<span class="pl-s"><span class="pl-pds">"</span>match_parent<span class="pl-pds">"</span></span>
     <span class="pl-e">android</span><span class="pl-e">:</span><span class="pl-e">layout_height</span>=<span class="pl-s"><span class="pl-pds">"</span>match_parent<span class="pl-pds">"</span></span>
     <span class="pl-e">app</span><span class="pl-e">:</span><span class="pl-e">penColor</span>=<span class="pl-s"><span class="pl-pds">"</span>@android:color/black<span class="pl-pds">"</span></span>
     /&gt;</pre></div>
<ol start="2">
<li>Konfigurasi atribut.</li>
</ol>
<ul>
<li><code>penMinWidth</code> - Minimal ketebalan garis tandatangan (default: 3dp).</li>
<li><code>penMaxWidth</code> - Maksimal ketebalan garis tandatangan (default: 7dp).</li>
<li><code>penColor</code> - Warna garis tandatangan (default: Color.BLACK).</li>
<li><code>velocityFilterWeight</code> - Ketebalan velocity (default: 0.9).</li>
<li><code>clearOnDoubleClick</code> - Double tap untuk clear area tandatangan (default: false)</li>
</ul>
<ol start="3">
<li>Mengambil data tandatangan</li>
</ol>
<ul>
<li><code>getSignatureBitmap()</code> - Gambar bitmap tandatangan dengan background putih</li>
<li><code>getTransparentSignatureBitmap()</code> - Gambar bitmap tandatangan dengan background transparan</li>
<li><code>getSignatureSvg()</code> - Gambar bitmap tandatangan dengan format SVG</li>
</ul>
</article>