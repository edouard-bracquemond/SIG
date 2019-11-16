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



 var map = new ol.Map({
        target: 'map',
        layers: layers,
        view: new ol.View({
          center: ol.proj.fromLonLat([1.927454, 47.836737]),
          zoom: 11
        })
      });