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
     {'INFO_FORMAT': 'application/json'});
   if (url) {
     fetch(url)
       .then(function (response) { return response.text(); })
       .then(function (html) {
            var obj = JSON.parse(html);
            var id = 0;
            if ( (obj['features']).length != 0){
                id = (obj['features'][0]['properties']['gid']);
            }
            InfoSelection.goToTreeActivity(id);
   //      document.getElementById('info').innerHTML = html;
       });
   }
 });

/*ici Point emplacement ajouté avec une nouvelle couche*/

refreshGPS(); //pour pas attendre 2000 la premeire fois
var interval = window.setInterval(refreshGPS, 2500);

function refreshGPS(){



        coordonnee = GPS.getFromAndroid();
        var pointPosition = new ol.Feature({
           geometry: new ol.geom.Point(
             ol.proj.fromLonLat([coor[1],coor[0]])
           ),
         });


         var myStyle = new ol.style.Style({
              image: new ol.style.Circle({
                radius: 9,
                fill: new ol.style.Fill({color: 'red'})
              })
            })

           pointPosition.setStyle(myStyle);

         var vectorSource = new ol.source.Vector({
           features: [pointPosition]
         });

         var pointVectorLayer = new ol.layer.Vector({
           source: vectorSource,
         });

        map.addLayer(pointVectorLayer);
        var intervalle = window.setInterval(removePoint, 2000);

        function removePoint(){
                window.map.removeLayer(pointVectorLayer);
        }
    }






