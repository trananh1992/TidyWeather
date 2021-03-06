import 'package:flutter/material.dart';

import '../widgets/LinkTextSpan.dart';

class AboutPage extends StatefulWidget {
  static const String route = '/about';

  @override
  _AboutPageState createState() => _AboutPageState();

}

class _AboutPageState extends State<AboutPage> {
  @override
  Widget build(BuildContext context) {
    final ThemeData themeData = Theme.of( context );
    final TextStyle textStyle = themeData.textTheme.bodyText1;
    final TextStyle linkStyle = themeData.textTheme.bodyText1.copyWith( color: themeData.accentColor );

    return Scaffold(
      appBar: AppBar(
        title: Text( 'About Tidy Weather' ),
      ),
      body: Container(
        padding: const EdgeInsets.symmetric( horizontal: 16.0, vertical: 12.0 ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            RichText(
              text: TextSpan(
                style: textStyle,
                children: <TextSpan>[
                  TextSpan(
                    text: 'Tidy Weather is lovingly crafted by ',
                  ),
                  LinkTextSpan(
                    style: linkStyle,
                    text: 'Gary',
                    url: 'https://pento.net',
                  ),
                  TextSpan(
                    text: '.',
                  ),
                ],
              ),
            ),
            Container(
              height: 20,
            ),
            RichText(
              text: TextSpan(
                style: textStyle,
                children: <TextSpan>[
                  TextSpan(
                    text: 'This is an Open Source application, you can view the source code, report bugs, and contribute fixes in the ',
                  ),
                  LinkTextSpan(
                    style: linkStyle,
                    text: 'Tidy Weather repository',
                    url: 'https://github.com/pento/TidyWeather/',
                  ),
                  TextSpan(
                    text: '.',
                  ),
                ],
              ),
            ),
            Expanded(
              child: Container(),
            ),
            RichText(
              text: TextSpan(
                style: textStyle,
                children: <TextSpan>[
                  TextSpan(
                    text: 'Some data in this app is sourced from the ',
                  ),
                  LinkTextSpan(
                    style: linkStyle,
                    text: 'Bureau of Meteorology',
                    url: 'http://www.bom.gov.au/data-access/3rd-party-attribution.shtml',
                  ),
                  TextSpan(
                    text: ', via ',
                  ),
                  LinkTextSpan(
                    style: linkStyle,
                    text: 'the WillyWeather API',
                    url: 'https://www.willyweather.com.au/info/api.html',
                  ),
                  TextSpan(
                    text: '.',
                  ),
                ],
              ),
            ),
            Container(
              height: 20,
            ),
            RichText(
              text: TextSpan(
                style: textStyle,
                children: <TextSpan>[
                  TextSpan(
                    text: 'UV observations courtesy of ARPANSA. ',
                  ),
                  LinkTextSpan(
                    style: linkStyle,
                    text: 'Disclaimer',
                    url: 'https://www.arpansa.gov.au/our-services/monitoring/ultraviolet-radiation-monitoring/ultraviolet-radation-data-information',
                  ),
                  TextSpan(
                    text: '.',
                  ),
                ],
              ),
            ),
            Container(
              height: 20,
            ),
            RichText(
              text: TextSpan(
                style: textStyle,
                children: <TextSpan>[
                  TextSpan(
                    text: 'Maps are © ',
                  ),
                  LinkTextSpan(
                    style: linkStyle,
                    text: 'OpenStreetMap',
                    url: 'https://www.openstreetmap.org/',
                  ),
                  TextSpan(
                    text: ' contributors.',
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
