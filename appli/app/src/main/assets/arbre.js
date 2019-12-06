 var osm_layer = new ol.layer.Tile({
	source: new ol.source.OSM()
});
var wmsSource = new ol.source.TileWMS({
                                url: 'https://psigo.beta9.ovh/geoserver/wms',
                                params: {'LAYERS': 'sigo:espace_publicev_arbres', 'TILED': true},
                                serverType: 'geoserver',
                                transition: 0
                              });
var wms_layer = new ol.layer.Tile({
          source: wmsSource
	});

var layers = [
	osm_layer,
		wms_layer
];

var coordonnee = GPS.getFromAndroid();
var coor=coordonnee.split(' ');
var latittude= parseFloat(coor[0]);
var longitude= parseFloat(coor[1]);

var view = new ol.View({
                     center: ol.proj.fromLonLat([longitude, latittude]),
                     zoom: 15
                   });
 var map = new ol.Map({
        target: 'map',
        layers: layers,
        view: view
      });
 map.on('singleclick', function(evt) {
 //  document.getElementById('info').innerHTML = '';
   var viewResolution = /** @type {number} */ (view.getResolution());
   var url = wmsSource.getFeatureInfoUrl(
     evt.coordinate, viewResolution, 'EPSG:3857',
     {'INFO_FORMAT': 'text/html'});
   if (url) {
     fetch(url)
       .then(function (response) { return response.text(); })
       .then(function (html) {
            InfoSelection.goToTreeActivity(html);
   //      document.getElementById('info').innerHTML = html;
       });
   }
 });