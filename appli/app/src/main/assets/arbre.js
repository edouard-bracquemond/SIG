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
                     zoom: 17
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
                type = (obj['features'][0]['properties']['type']);
                genre = (obj['features'][0]['properties']['genre']);
                espece = (obj['features'][0]['properties']['espece']);
                variete = (obj['features'][0]['properties']['variete']);
                $('.modal-title').html("Arbre de type "+type)
                $('#data').html("Genre: "+genre+"<br> Espèce: "+espece+"<br>Variété: "+variete);
                $('#id').val(""+id);
                $('#basicModal').modal('show');
            }
       });
   }
 });


function uploadPhoto() {
    var id = $('#id').val();
    InfoSelection.goToTreeActivity(id);
 }


 // Objet géographique de la position de géolocalisation
 	var ObjPosition = new ol.Feature();
 	// Attribution d'un style à l'objet
 	ObjPosition.setStyle(new ol.style.Style({
 		image: new ol.style.Circle({
 			radius: 6,
 			fill: new ol.style.Fill({
 				color: '#3399CC'
 			}),
 			stroke: new ol.style.Stroke({
 				color: '#fff',
 				width: 2
 			})
 		})
 	}));
 	// Géolocalisation
 	var geolocation = new ol.Geolocation({
 	  enableHighAccuracy: true,
 	  // On déclenche la géolocalisation
 	  tracking: true,
 	  // Important : Projection de la carte
 	  projection: view.getProjection()
 	});
 	// On scrute les changements des propriétés
 	geolocation.on('change:position', function(evt) {
 	    console.log("La geoloc a changé "+geolocation.getPosition());
 		var position = geolocation.getPosition();
 		// On transforme la projection des coordonnées
 		var newPosition=ol.proj.transform(position, 'EPSG:3857','EPSG:4326');
 		// Attribution de la géométrie de ObjPosition avec les coordonnées de la position
 		ObjPosition.setGeometry( position ? new ol.geom.Point(position) : null );
 	});

 	var precisionFeature = new ol.Feature();
 	geolocation.on('change:accuracyGeometry', function(evt){
        console.log("La geoloc a changé "+geolocation.getAccuracyGeometry());
        precisionFeature.setGeometry(geolocation.getAccuracyGeometry());
 	});
 	// On alerte si une erreur est trouvée
 	geolocation.on('error', function(erreur) {
 		console.log('Echec de la géolocalisation : ' +erreur.message);
 	});


 	// Source du vecteur contenant l'objet géographique
 	var sourceVecteur = new ol.source.Vector({
 			features: [precisionFeature,ObjPosition]
 	});

 	var pointVectorLayer = new ol.layer.Vector({
               source: sourceVecteur,
             });

    map.addLayer(pointVectorLayer);


/*ici Point emplacement ajouté avec une nouvelle couche*/

/*
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
                fill: new ol.style.Fill({color: 'red'}),
                stroke: new ol.style.Stroke({color: '#fff', width: 2})
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

/*var geolocation = new ol.Geolocation({
  // enableHighAccuracy must be set to true to have the heading value.
  trackingOptions: {
    enableHighAccuracy: true
  },
  projection: view.getProjection()
});

geolocation.setTracking(true);


// handle geolocation error.
geolocation.on('error', function(error) {

});

var accuracyFeature = new ol.Feature();
geolocation.on('change:accuracyGeometry', function() {
  accuracyFeature.setGeometry(geolocation.getAccuracyGeometry());
});

var positionFeature = new ol.Feature();
positionFeature.setStyle(new ol.Style({
  image: new ol.CircleStyle({
    radius: 6,
    fill: new ol.Style.Fill({
      color: '#3399CC'
    }),
    stroke: new ol.Style.Stroke({
      color: '#fff',
      width: 2
    })
  })
}));

geolocation.on('change:position', function() {
  var coordinates = geolocation.getPosition();
  positionFeature.setGeometry(coordinates ?
    new Point(coordinates) : null);
});

new VectorLayer({
  map: map,
  source: new VectorSource({
    features: [accuracyFeature, positionFeature]
  })
});
*/




