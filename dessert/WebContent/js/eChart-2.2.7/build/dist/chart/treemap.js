define("echarts/chart/treemap", ["require", "./base", "zrender/tool/area", "zrender/shape/Rectangle", "zrender/shape/Text", "zrender/shape/Line", "../layout/TreeMap", "../data/Tree", "../config", "../util/ecData", "zrender/config", "zrender/tool/event", "zrender/tool/util", "zrender/tool/color", "../chart"], function (e) {
    function t(e, t, n, a, o) {
        i.call(this, e, t, n, a, o), this.refresh(a);
        var r = this;
        r._onclick = function (e) {
            return r.__onclick(e)
        }, r.zr.on(V.EVENT.CLICK, r._onclick)
    }

    var i = e("./base"), n = e("zrender/tool/area"), a = e("zrender/shape/Rectangle"), o = e("zrender/shape/Text"), r = e("zrender/shape/Line"), s = e("../layout/TreeMap"), l = e("../data/Tree"), h = e("../config");
    h.treemap = {
        zlevel: 0,
        z: 1,
        calculable: !1,
        clickable: !0,
        center: ["50%", "50%"],
        size: ["80%", "80%"],
        root: "",
        itemStyle: {
            normal: {
                label: {
                    show: !0,
                    x: 5,
                    y: 12,
                    textStyle: {
                        align: "left",
                        color: "#000",
                        fontFamily: "Arial",
                        fontSize: 13,
                        fontStyle: "normal",
                        fontWeight: "normal"
                    }
                },
                breadcrumb: {show: !0, textStyle: {}},
                borderWidth: 1,
                borderColor: "#ccc",
                childBorderWidth: 1,
                childBorderColor: "#ccc"
            }, emphasis: {}
        }
    };
    var m = e("../util/ecData"), V = e("zrender/config"), U = (e("zrender/tool/event"), e("zrender/tool/util")), d = e("zrender/tool/color");
    return t.prototype = {
        type: h.CHART_TYPE_TREEMAP, refresh: function (e) {
            this.clear(), e && (this.option = e, this.series = this.option.series), this._treesMap = {};
            for (var t = this.series, i = this.component.legend, n = 0; n < t.length; n++)if (t[n].type === h.CHART_TYPE_TREEMAP) {
                t[n] = this.reformOption(t[n]);
                var a = t[n].name || "";
                if (this.selectedMap[a] = i ? i.isSelected(a) : !0, !this.selectedMap[a])continue;
                this._buildSeries(t[n], n)
            }
        }, _buildSeries: function (e, t) {
            var i = l.fromOptionData(e.name, e.data);
            this._treesMap[t] = i;
            var n = e.root && i.getNodeById(e.root) || i.root;
            this._buildTreemap(n, t)
        }, _buildTreemap: function (e, t) {
            for (var i = this.shapeList, n = 0; n < i.length;) {
                var a = i[n];
                m.get(a, "seriesIndex") === t ? (this.zr.delShape(i[n]), i.splice(n, 1)) : n++
            }
            for (var o = i.length, r = this.series[t], l = r.itemStyle, h = this.parsePercent(r.size[0], this.zr.getWidth()) || 400, V = this.parsePercent(r.size[1], this.zr.getHeight()) || 500, U = this.parseCenter(this.zr, r.center), d = U[0] - .5 * h, p = U[1] - .5 * V, c = h * V, u = 0, y = [], g = e.children, n = 0; n < g.length; n++)u += g[n].data.value;
            for (var b = 0; b < g.length; b++)y.push(g[b].data.value * c / u);
            for (var f = new s({x: d, y: p, width: h, height: V}), k = f.run(y), _ = 0; _ < k.length; _++) {
                var x = g[_].data, L = k[_], W = [x.itemStyle, l], X = this.deepMerge(W);
                X.normal.color || (X.normal.color = this.zr.getColor(_)), X.emphasis.color || (X.emphasis.color = X.normal.color), this._buildItem(x, X, L, t, _), x.children && this._buildChildrenTreemap(x.children, X, L, t)
            }
            this.query(r, "itemStyle.normal.breadcrumb.show") && this._buildBreadcrumb(e, t, d, p + V);
            for (var n = o; n < i.length; n++)this.zr.addShape(i[n])
        }, _buildItem: function (e, t, i, n, a) {
            var o = this.series, r = this.getRectangle(e, t, i);
            m.pack(r, o[n], n, e, a, e.name), this.shapeList.push(r)
        }, getRectangle: function (e, t, i) {
            var n = t.emphasis, o = t.normal, r = this.getLabel(t, i, e.name, e.value), s = this.option.hoverable, l = {
                zlevel: this.getZlevelBase(),
                z: this.getZBase(),
                hoverable: s,
                clickable: !0,
                style: U.merge({
                    x: i.x,
                    y: i.y,
                    width: i.width,
                    height: i.height,
                    brushType: "both",
                    color: o.color,
                    lineWidth: o.borderWidth,
                    strokeColor: o.borderColor
                }, r.style, !0),
                highlightStyle: U.merge({
                    color: n.color,
                    lineWidth: n.borderWidth,
                    strokeColor: n.borderColor
                }, r.highlightStyle, !0)
            };
            return new a(l)
        }, getLabel: function (e, t, i, a) {
            var o = e.normal.label.textStyle, r = [e.emphasis.label.textStyle, o], s = this.deepMerge(r), l = e.normal.label.formatter, h = this.getLabelText(i, a, l), m = this.getFont(o), V = n.getTextWidth(h, m), U = n.getTextHeight(h, m), d = this.deepQuery([e.emphasis, e.normal], "label.formatter"), p = this.getLabelText(i, a, d), c = this.getFont(s), u = n.getTextWidth(h, c), y = n.getTextHeight(h, c);
            e.normal.label.show ? (e.normal.label.x + V > t.width || e.normal.label.y + U > t.height) && (h = "") : h = "", e.emphasis.label.show ? (s.x + u > t.width || s.y + y > t.height) && (p = "") : p = "";
            var g = {
                style: {
                    textX: t.x + e.normal.label.x,
                    textY: t.y + e.normal.label.y,
                    text: h,
                    textPosition: "specific",
                    textColor: o.color,
                    textFont: m
                },
                highlightStyle: {
                    textX: t.x + e.emphasis.label.x,
                    textY: t.y + e.emphasis.label.y,
                    text: p,
                    textColor: s.color,
                    textPosition: "specific"
                }
            };
            return g
        }, getLabelText: function (e, t, i) {
            return i ? "function" == typeof i ? i.call(this.myChart, e, t) : "string" == typeof i ? (i = i.replace("{b}", "{b0}").replace("{c}", "{c0}"), i = i.replace("{b0}", e).replace("{c0}", t)) : void 0 : e
        }, _buildChildrenTreemap: function (e, t, i, n) {
            for (var a = i.width * i.height, o = 0, r = [], l = 0; l < e.length; l++)o += e[l].value;
            for (var h = 0; h < e.length; h++)r.push(e[h].value * a / o);
            for (var V = new s({
                x: i.x,
                y: i.y,
                width: i.width,
                height: i.height
            }), U = V.run(r), d = t.normal.childBorderWidth, p = t.normal.childBorderColor, c = 0; c < U.length; c++) {
                var u = U[c], y = [];
                i.y.toFixed(2) !== u.y.toFixed(2) && y.push(this._getLine(u.x, u.y, u.x + u.width, u.y, d, p)), i.x.toFixed(2) !== u.x.toFixed(2) && y.push(this._getLine(u.x, u.y, u.x, u.y + u.height, d, p)), (i.y + i.height).toFixed(2) !== (u.y + u.height).toFixed(2) && y.push(this._getLine(u.x, u.y + u.height, u.x + u.width, u.y + u.height, d, p)), (i.x + i.width).toFixed(2) !== (u.x + u.width).toFixed(2) && y.push(this._getLine(u.x + u.width, u.y, u.x + u.width, u.y + u.height, d, p));
                for (var g = 0; g < y.length; g++)m.set(y[g], "seriesIndex", n), this.shapeList.push(y[g])
            }
        }, _getLine: function (e, t, i, n, a, o) {
            var s = {
                zlevel: this.getZlevelBase(),
                z: this.getZBase(),
                hoverable: !1,
                style: {xStart: e, yStart: t, xEnd: i, yEnd: n, lineWidth: a, strokeColor: o}
            };
            return new r(s)
        }, _buildBreadcrumb: function (e, t, i, n) {
            for (var a = [], r = e; r;)a.unshift(r.data.name), r = r.parent;
            for (var s = this.series[t], l = this.query(s, "itemStyle.normal.breadcrumb.textStyle") || {}, h = this.query(s, "itemStyle.emphasis.breadcrumb.textStyle") || {}, V = {
                y: n + 10,
                textBaseline: "top",
                textAlign: "left",
                color: l.color,
                textFont: this.getFont(l)
            }, p = {
                brushType: "fill",
                color: h.color || d.lift(l.color, -.3),
                textFont: this.getFont(h)
            }, c = 0; c < a.length; c++) {
                var u = new o({
                    zlevel: this.getZlevelBase(),
                    z: this.getZBase(),
                    style: U.merge({x: i, text: a[c] + (a.length - 1 - c ? " > " : "")}, V),
                    clickable: !0,
                    highlightStyle: p
                });
                m.set(u, "seriesIndex", t), m.set(u, "name", a[c]), i += u.getRect(u.style).width, this.shapeList.push(u)
            }
        }, __onclick: function (e) {
            var t = e.target;
            if (t) {
                var i = m.get(t, "seriesIndex"), n = m.get(t, "name"), a = this._treesMap[i], o = a.getNodeById(n);
                o && o.children.length && this._buildTreemap(o, i)
            }
        }
    }, U.inherits(t, i), e("../chart").define("treemap", t), t
}), define("echarts/layout/TreeMap", ["require"], function () {
    function e(e) {
        ({x: e.x, y: e.y, width: e.width, height: e.height});
        this.x = e.x, this.y = e.y, this.width = e.width, this.height = e.height
    }

    return e.prototype.run = function (e) {
        var t = [];
        return this._squarify(e, {x: this.x, y: this.y, width: this.width, height: this.height}, t), t
    }, e.prototype._squarify = function (e, t, i) {
        var n = "VERTICAL", a = t.width, o = t.height;
        t.width < t.height && (n = "HORIZONTAL", a = t.height, o = t.width);
        for (var r = this._getShapeListInAbstractRow(e, a, o), s = 0; s < r.length; s++) {
            r[s].x = 0, r[s].y = 0;
            for (var l = 0; s > l; l++)r[s].y += r[l].height
        }
        var h = {};
        if ("VERTICAL" == n) {
            for (var m = 0; m < r.length; m++)i.push({
                x: r[m].x + t.x,
                y: r[m].y + t.y,
                width: r[m].width,
                height: r[m].height
            });
            h = {x: r[0].width + t.x, y: t.y, width: t.width - r[0].width, height: t.height}
        } else {
            for (var V = 0; V < r.length; V++)i.push({
                x: r[V].y + t.x,
                y: r[V].x + t.y,
                width: r[V].height,
                height: r[V].width
            });
            h = {x: t.x, y: t.y + r[0].width, width: t.width, height: t.height - r[0].width}
        }
        var U = e.slice(r.length);
        0 !== U.length && this._squarify(U, h, i)
    }, e.prototype._getShapeListInAbstractRow = function (e, t, i) {
        if (1 === e.length)return [{width: t, height: i}];
        for (var n = 1; n < e.length; n++) {
            var a = this._placeFixedNumberRectangles(e.slice(0, n), t, i), o = this._placeFixedNumberRectangles(e.slice(0, n + 1), t, i);
            if (this._isFirstBetter(a, o))return a
        }
    }, e.prototype._placeFixedNumberRectangles = function (e, t, i) {
        for (var n = e.length, a = [], o = 0, r = 0; r < e.length; r++)o += e[r];
        for (var s = o / i, l = 0; n > l; l++) {
            var h = i * e[l] / o;
            a.push({width: s, height: h})
        }
        return a
    }, e.prototype._isFirstBetter = function (e, t) {
        var i = e[0].height / e[0].width;
        i = i > 1 ? 1 / i : i;
        var n = t[0].height / t[0].width;
        return n = n > 1 ? 1 / n : n, Math.abs(i - 1) <= Math.abs(n - 1) ? !0 : !1
    }, e
}), define("echarts/data/Tree", ["require", "zrender/tool/util"], function (e) {
    function t(e, t) {
        this.id = e, this.depth = 0, this.height = 0, this.children = [], this.parent = null, this.data = t || null
    }

    function i(e) {
        this.root = new t(e)
    }

    var n = e("zrender/tool/util");
    return t.prototype.add = function (e) {
        var t = this.children;
        e.parent !== this && (t.push(e), e.parent = this)
    }, t.prototype.remove = function (e) {
        var t = this.children, i = n.indexOf(t, e);
        i >= 0 && (t.splice(i, 1), e.parent = null)
    }, t.prototype.traverse = function (e, t) {
        e.call(t, this);
        for (var i = 0; i < this.children.length; i++)this.children[i].traverse(e, t)
    }, t.prototype.updateDepthAndHeight = function (e) {
        var t = 0;
        this.depth = e;
        for (var i = 0; i < this.children.length; i++) {
            var n = this.children[i];
            n.updateDepthAndHeight(e + 1), n.height > t && (t = n.height)
        }
        this.height = t + 1
    }, t.prototype.getNodeById = function (e) {
        if (this.id === e)return this;
        for (var t = 0; t < this.children.length; t++) {
            var i = this.children[t].getNodeById(e);
            if (i)return i
        }
    }, i.prototype.traverse = function (e, t) {
        this.root.traverse(e, t)
    }, i.prototype.getSubTree = function (e) {
        var t = this.getNodeById(e);
        if (t) {
            var n = new i(t.id);
            return n.root = t, n
        }
    }, i.prototype.getNodeById = function (e) {
        return this.root.getNodeById(e)
    }, i.fromOptionData = function (e, n) {
        function a(e, i) {
            var n = new t(e.name, e);
            i.add(n);
            var o = e.children;
            if (o)for (var r = 0; r < o.length; r++)a(o[r], n)
        }

        var o = new i(e), r = o.root;
        r.data = {name: e, children: n};
        for (var s = 0; s < n.length; s++)a(n[s], r);
        return o.root.updateDepthAndHeight(0), o
    }, i.fromGraph = function (e) {
        function n(t) {
            for (var i = e.getNodeById(t.id), a = 0; a < i.outEdges.length; a++) {
                var r = i.outEdges[a], s = o[r.node2.id];
                t.children.push(s), n(s)
            }
        }

        for (var a = {}, o = {}, r = 0; r < e.nodes.length; r++) {
            var s, l = e.nodes[r];
            0 === l.inDegree() ? (a[l.id] = new i(l.id), s = a[l.id].root) : s = new t(l.id), s.data = l.data, o[l.id] = s
        }
        var h = [];
        for (var m in a)n(a[m].root), a[m].root.updateDepthAndHeight(0), h.push(a[m]);
        return h
    }, i
});