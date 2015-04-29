
/***** Revenue Chart *****/
new Morris.Line({
  // ID of the element in which to draw the chart.
  element: 'revenue-chart',
  // Chart data records -- each entry in this array corresponds to a point on
  // the chart.
  data: [
    { year: '2008', value: 20 },
    { year: '2009', value: 10 },
    { year: '2010', value: 5 },
    { year: '2011', value: 5 },
    { year: '2012', value: 20 }
  ],
  // The name of the data record attribute that contains x-values.
  xkey: 'year',
  // A list of names of data record attributes that contain y-values.
  ykeys: ['value'],
  // Labels for the ykeys -- will be displayed when you hover over the
  // chart.
  labels: ['Value'],
  lineColors: ['#ffffff'],
  pointFillColors: ['#5d4bb0'],
  gridTextColor: ['#c5baf6']
});

/***** Week Sales *****/
new Morris.Line({
  // ID of the element in which to draw the chart.
  element: 'monthly-sales',
  // Chart data records -- each entry in this array corresponds to a point on
  // the chart.
  data: [
    { day: 'SUN', value: 250 },
    { day: 'MON', value: 210 },
    { day: 'TUE', value: 140 },
    { day: 'WED', value: 41 },
    { day: 'THU', value: 380 },
    { day: 'FRI', value: 410 },
    { day: 'SAT', value: 233 }
  ],
  smooth: false,
  grid:false,
  axes:true,
  parseTime: false,
  // The name of the data record attribute that contains x-values.
  xkey: 'day',
  // A list of names of data record attributes that contain y-values.
  ykeys: ['value'],
  // Labels for the ykeys -- will be displayed when you hover over the
  // chart.
  labels: '',
  lineColors: ['#ffffff'],
  pointFillColors: ['#5ac8d2'],
  gridTextColor: ['#218c95']
});
