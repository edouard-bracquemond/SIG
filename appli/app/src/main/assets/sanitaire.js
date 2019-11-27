 var osm_layer = new ol.layer.Tile({
	source: new ol.source.OSM()
});

var wms_layer = new ol.layer.Tile({
          source: new ol.source.TileWMS({
            url: 'https://psigo.beta9.ovh/geoserver/wms',
            params: {'LAYERS': 'sigo:espace_public_ev_sanitaires', 'TILED': true},
            serverType: 'geoserver',
            transition: 0
          })
	});

var layers = [
	osm_layer,
		wms_layer
];

var coordonnee = GPS.getFromAndroid();
var coor=coordonnee.split(' ');
var latittude= parseFloat(coor[0]);
var longitude= parseFloat(coor[1]);


 var map = new ol.Map({
        target: 'map',
        layers: layers,
        view: new ol.View({
          center: ol.proj.fromLonLat([longitude, latittude]),
          zoom: 15
        })
      });