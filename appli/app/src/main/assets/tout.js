 var osm_layer = new ol.layer.Tile({
	source: new ol.source.OSM()
});

var wmsSource = new ol.source.TileWMS({
            url: 'https://psigo.beta9.ovh/geoserver/wms',
            params: {'LAYERS': 'sigo:tout', 'TILED': true},
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
                //corbeille
                corbeille_t = (obj['features'][0]['properties']['corbeille_t']);
                corbeille_l = (obj['features'][0]['properties']['corbeille_l']);
                corbeille_s = (obj['features'][0]['properties']['corbeille_s']);
                corbeille_r = (obj['features'][0]['properties']['corbeille_r']);
                corbeille_f = (obj['features'][0]['properties']['corbeille_f']);
                //dechet
                type_flux = (obj['features'][0]['properties']['type_flux']);
                rue = (obj['features'][0]['properties']['rue']);
                //sanitaire
                san_gest = (obj['features'][0]['properties']['san_gest']);
                san_horaire = (obj['features'][0]['properties']['san_horaire']);
                san_sect = (obj['features'][0]['properties']['san_sect']);
                san_remarq = (obj['features'][0]['properties']['san_remarq']);
                san_handi = (obj['features'][0]['properties']['san_handi']);
                //banc
                banc_detail = (obj['features'][0]['properties']['banc_detail']);
                banc_remarq = (obj['features'][0]['properties']['banc_remarq']);
                banc_secteu = (obj['features'][0]['properties']['banc_secteu']);
                //arbre
                type = (obj['features'][0]['properties']['type']);
                genre = (obj['features'][0]['properties']['genre']);
                espece = (obj['features'][0]['properties']['espece']);
                variete = (obj['features'][0]['properties']['variete']);


                if(corbeille_t != null){ // on clique sur une corbeille
                    $('.modal-title').html("Corbeille")
                    $('#data').html("Type: " + corbeille_t+ "<br> Rue: " + corbeille_l + "<br> Secteur: " + corbeille_s +
                        "<br> Ramassage: " + corbeille_f );
                    $('#id').val(""+id);
                    $('#basicModal').modal('show');
                }
                if(type_flux != null){ // on clique sur un dechet
                    $('.modal-title').html("Dechet")
                    $('#data').html("Type: " + type_flux+ "<br> Rue: " + rue);
                    $('#id').val(""+id);
                    $('#basicModal').modal('show');
                }
                if(banc_detail != null){  // on clique sur un banc
                    $('.modal-title').html("Banc")
                    $('#data').html("Detail: " + banc_detail+ "<br> Remarque: " + banc_remarq + "<br> Secteur: " + banc_secteu);
                    $('#id').val(""+id);
                    $('#basicModal').modal('show');
                }
                if(san_gest != null){  // on clique sur un sanitaire
                     $('.modal-title').html("Sanitaire")
                     $('#data').html("Gestionnaire: " + san_gest+ "<br> Horaire: " + san_horaire + "<br> Secteur: " + san_sect +
                       "<br> Handicapé: " + san_handi);
                      $('#id').val(""+id);
                      $('#basicModal').modal('show');
                }
                if(type != null) { //on clique sur un arbre
                    $('.modal-title').html("Arbre de type "+type)
                    $('#data').html("Genre: "+genre+"<br> Espèce: "+espece+"<br>Variété: "+variete);
                    $('#id').val(""+id);
                    $('#basicModal').modal('show');
                }
            }
       });
   }
 });

function signaler() {
    var id = $('#id').val();
    InfoSelection.goToSignalerActivity("Corbeille",id);
}

function signalements() {
    var id = $('#id').val();
    InfoSelection.gotoReportActivity("Corbeille",id);
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
 	    //console.log("La geoloc a changé "+geolocation.getPosition());
 		var position = geolocation.getPosition();

 		//view.setCenter(position); //On ne fait pas un suivi
 		// On transforme la projection des coordonnées
 		var newPosition=ol.proj.transform(position, 'EPSG:3857','EPSG:4326');
 		// Attribution de la géométrie de ObjPosition avec les coordonnées de la position
 		ObjPosition.setGeometry( position ? new ol.geom.Point(position) : null );
 	});

 	var precisionFeature = new ol.Feature();
 	geolocation.on('change:accuracyGeometry', function(evt){
        //console.log("La geoloc a changé "+geolocation.getAccuracyGeometry());
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

    //Point de position

    const locate = document.createElement('div');
    locate.className = 'ol-control ol-unselectable locate';
    locate.innerHTML = '<button title="Locate me">◎</button>';

    locate.addEventListener('click', function() {
      if (!sourceVecteur.isEmpty()) {
        map.getView().fit(sourceVecteur.getExtent(), {
          maxZoom: 17,
          duration: 500
        });
      }
    });


    map.addControl(new ol.control.Control({
      element: locate
    }));
